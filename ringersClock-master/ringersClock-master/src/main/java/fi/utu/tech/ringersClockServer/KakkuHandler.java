package fi.utu.tech.ringersClockServer;

import fi.utu.tech.entities.Kakku;
import fi.utu.tech.entities.WakeUpGroup;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class KakkuHandler extends Thread{

    private Socket client;
    private WakeUpService wus;
    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;

    public KakkuHandler(Socket socket){
        System.out.println("New connection from "+socket.getInetAddress());
        this.client = socket;
        ServerSocketListener.clients.add(this);
    }

    @Override
    public void run(){
        try{
            wus = new WakeUpService();
            this.objOut = new ObjectOutputStream(this.client.getOutputStream());
            this.objIn = new ObjectInputStream(this.client.getInputStream());
            sendKakku(new Kakku("päivitäRyhmät", createList(ServerSocketListener.groups)));

            try{
                while(true){
                    Object o = objIn.readObject();
                    Kakku kakku = (o instanceof Kakku) ? (Kakku) o : null;
                    smashTheCake(kakku);
                }
            } catch (IOException exe){
                objIn.close();
                objOut.close();
            }
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    /**
     * Lähetetään clientille kakku ObjectOutputStreaminä
     */
    public void sendKakku(Kakku kakku) {
        try {
            System.out.println("Lähetetään kakku!");
            this.objOut.writeObject(kakku);
            this.objOut.flush();
        } catch (IOException e) {
            System.out.println("I/O exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Kakkujen lähetys useammalle clientille
     */
    private void shareCakeWithClients(Kakku kakku){
        for(KakkuHandler kh : ServerSocketListener.clients){
            kh.sendKakku(kakku);
        }
    }

    /**
     * Tekee WakeUpGroup-tyyppisen ArrayListin ryhmistä
     */
    private ArrayList<WakeUpGroup> createList(HashMap<WakeUpGroup, ArrayList<KakkuHandler>> groups){
        ArrayList<WakeUpGroup> groupList = new ArrayList<>();
        for(WakeUpGroup wug : groups.keySet()){
            groupList.add(wug);
        }
        return groupList;
    }

    /**
     * Tarkistaa, että ryhmä on oikea ID:n perusteella
     */
    private WakeUpGroup getGroupByGroup(WakeUpGroup group){
        for(WakeUpGroup wug : ServerSocketListener.groups.keySet()){
            System.out.println("Server groupID: "+wug.getID()+ " Client GroupID: "+group.getID()+" Equals: "+wug.equals(group));
            if(wug.equals(group)){
                return wug;
            }
        }
        return null;
    }

    /**
     * Tarkistaa, että ryhmä on oikea clientin perusteella
     */
    private WakeUpGroup getGroupByUser(KakkuHandler client){
        for(WakeUpGroup wug: ServerSocketListener.groups.keySet()) {
            for(KakkuHandler kh : ServerSocketListener.groups.get(wug)){
                if(client.equals(kh))
                    return wug;
            }
        }
        return null;
    }

    /**
     * Tarkistaa, onko client jo ryhmässä
     */
    private boolean isInGroup(KakkuHandler client){
        for(ArrayList<KakkuHandler> list : ServerSocketListener.groups.values()){
            for(KakkuHandler kh : list){
                if(kh.equals(client)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Poistetaan client ryhmästä ja nollataan clientin herätys GUI:sta
     */
    private void removeUserFromGroup(WakeUpGroup wug, KakkuHandler client){
        ArrayList<KakkuHandler> kh = ServerSocketListener.groups.get(wug);
        if(kh.contains(client)){
            if(kh.indexOf(client) == 0) {
                removeGroup(wug, "Ryhmä \""+wug.getName()+"\" poistettu!");
            }else{
                kh.remove(client);
                sendKakku(new Kakku("poistaHerätys", null,"Poistuit ryhmästä \""+wug.getName()+"\"!"));
            }
        }
    }

    /**
     * Poistaa ryhmän, poistaa ryhmäläisten herätyksen ja päivittää ryhmän ryhmälistaan GUI:hin
     */
    public void removeGroup(WakeUpGroup wug, String statusMsg){
        ArrayList<KakkuHandler> kh = ServerSocketListener.groups.get(wug);
        for(KakkuHandler client : kh){
            client.sendKakku(new Kakku("poistaHerätys", null, statusMsg));
        }
        ServerSocketListener.groups.remove(wug);
        shareCakeWithClients(new Kakku("päivitäRyhmät", createList(ServerSocketListener.groups)));
    }

    /**
     * Herätetään ryhmäläiset, leader jää herätyksestä pois
     */
    private void alarmAllFromTo(WakeUpGroup wug, int from, int to){
        ArrayList<KakkuHandler> clients = ServerSocketListener.groups.get(wug);
        for(int i = from; i < to; i++){
            clients.get(i).sendKakku(new Kakku("herätä"));
        }
    }

    /**
     * Luodaan uusi ryhmä ja päivitetään ryhmälista GUI:ihin
     */
    private void createNewGroup(WakeUpGroup wug){
        wug.setID(ServerSocketListener.index++);
        ServerSocketListener.groups.put(wug, new ArrayList<>(Arrays.asList(this)));
        sendKakku(new Kakku("päivitäHerätysAika", wug, "Ryhmä \""+wug.getName()+"\" luotu!"));
        shareCakeWithClients(new Kakku("päivitäRyhmät", createList(ServerSocketListener.groups)));
    }

    /**
     * Client liittyy ryhmään
     */
    private void joinGroup(WakeUpGroup wug){
        ArrayList<KakkuHandler> lisättävä = ServerSocketListener.groups.get(wug);
        if (!lisättävä.contains(this))
            lisättävä.add(this);
        ServerSocketListener.groups.replace(wug, lisättävä);
        ServerSocketListener.groups.get(wug).get(0).sendKakku(new Kakku("statusMsg", "Uusi jäsen liittyi ryhmään!"));
        sendKakku(new Kakku("päivitäHerätysAika", wug, "Liityit ryhmään: \"" +wug.getName()+"\"!"));
    }

    /**
     * Kakun purkaminen ymmärrettävään muotoon ObjectOutputStreamistä
     */
    public void smashTheCake(Object o) {
        if(!(o instanceof Kakku))
            return;

        Kakku kakku = (Kakku) o;
        WakeUpGroup wug = kakku.getTayte() instanceof WakeUpGroup ? (WakeUpGroup) kakku.getTayte() : null;

        switch(kakku.getKuorrute()){
            case "luoRyhmä":
                if(!getErrorMsg(kakku.getKuorrute()))
                    createNewGroup(wug);
            break;

            case "liityRyhmään":
                if(!getErrorMsg(kakku.getKuorrute()))
                    joinGroup(getGroupByGroup(wug));
            break;

            case "poistuRyhmästä":
                if(!getErrorMsg(kakku.getKuorrute()))
                    removeUserFromGroup(getGroupByUser(this), this);
            break;

            case "herätäRyhmä":
                wug = getGroupByUser(this);
                alarmAllFromTo(wug, 1, ServerSocketListener.groups.get(wug).size());
                removeGroup(wug, "Ryhmän \""+wug.getName()+"\" herätys suoritettu!");
            break;

            case "peruutaHerätys":
                wug = getGroupByGroup(wug);
                removeGroup(wug, "Ryhmän \""+wug.getName()+"\" heräätys peruttu!");
            break;

            case "päivitäRyhmät":
                sendKakku(new Kakku("päivitäRyhmät", createList(ServerSocketListener.groups)));
            break;
            default:
                System.out.println("Kuorutetta '"+kakku.getKuorrute()+"' ei löytynyt!");
        }
    }

    private boolean getErrorMsg(String kuorrute) {
        if (kuorrute == null)
            return true;

        if (isInGroup(this)) {
            switch (kuorrute) {
                case "luoRyhmä":
                    sendKakku(new Kakku("statusMsg", "Et voi luoda ryhmää, koska olet jo yhden ryhmän jäsen!"));
                    return true;
                case "liityRyhmään":
                    sendKakku(new Kakku("statusMsg", "Et voi liittyä toiseen ryhmää, koska olet yhden ryhmän jäsen!"));
                    return true;
            }
        }
        if (!isInGroup(this)) {
            switch (kuorrute) {
                case "poistuRyhmästä":
                    sendKakku(new Kakku("statusMsg", "Et ole liittynyt ryhmään!"));
                    return true;
            }
        }
        return false;
    }
}
