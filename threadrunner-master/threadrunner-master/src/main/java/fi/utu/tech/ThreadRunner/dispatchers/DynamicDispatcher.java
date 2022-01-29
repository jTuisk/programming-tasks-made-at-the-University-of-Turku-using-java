package fi.utu.tech.ThreadRunner.dispatchers;

/*
 * Luokka, jossa toteutetaan dynaaminen tehtävien jako ts. työn tehtävä 2
 * 
* @version     1.0                 
* @since       1.0          
*/

import fi.utu.tech.ThreadRunner.tasks.Countable;
import fi.utu.tech.ThreadRunner.tasks.TaskFactory;

import java.util.ArrayList;
import java.util.concurrent.*;

public class DynamicDispatcher implements Dispatcher {

	public void dispatch(ControlSet controlSet){
		try {
			Countable co = TaskFactory.createTask(controlSet.getTaskType());
			ArrayList<Integer> taskList = co.generate(controlSet.getAmountTasks(), controlSet.getMaxTime());
			ExecutorService executor = Executors.newFixedThreadPool(controlSet.getThreadCount());

			/**
			 * jaetaan työtehtävät seteiksi
			 */
			ArrayList<ArrayList<Integer>> taskSets = getSets( taskList, controlSet.getThreadCount());

			/**
			 * jaetaan tehtäväsetit kakunsyöjinä ExecutorServicelle
			 */
			for (int i = 0; i < taskSets.size(); i++) {
				Kakunsyöjä ks = new Kakunsyöjä(taskSets.get(i), controlSet.getWorkerType());
				executor.execute(ks);
			}

			System.out.println("Ei uusia säikeitä, suoritetaan jäljellä olevat loppuun. \nOdotetaan kaikkien säikeiden päättymistä.");
			executor.shutdown();
			executor.awaitTermination(60, TimeUnit.SECONDS);
			System.out.println("Kaikki säikeet suoritettu.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ArrayList<Integer>> getSets(ArrayList<Integer> tasks, int threadCount){
		ArrayList<ArrayList<Integer>> tempListSet = new ArrayList<>();

		int taskListAmo = taskListAmo(tasks.size(), threadCount);

		if(threadCount < 1 ||tasks.isEmpty())
			return tempListSet;

		for(int i = 0; i <= taskListAmo; i++){
			ArrayList<Integer> tempList = new ArrayList<>();

			int tasksForEachList = (int) Math.ceil(tasks.size()/(float)taskListAmo);
			int tasksForCurrentList = i < (taskListAmo) ?
					tasksForEachList :
					tasks.size()-(tasksForEachList*taskListAmo);

			for(int j = 0; j < tasksForCurrentList; j++){
				try {
					tempList.add(tasks.get(i*tasksForEachList+j));
				} catch (IndexOutOfBoundsException e){
					break;
				}
			}
			tempListSet.add(tempList);
		}
		return tempListSet;
	}

	public static int taskListAmo(int amo, int threadCount){
		return  threadCount > 1 ?
				(int)Math.ceil(amo/threadCount)*2:
				threadCount;
	}
}
