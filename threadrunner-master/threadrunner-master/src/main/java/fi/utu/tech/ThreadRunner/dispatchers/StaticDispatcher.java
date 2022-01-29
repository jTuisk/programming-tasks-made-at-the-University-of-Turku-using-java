package fi.utu.tech.ThreadRunner.dispatchers;

/*
 * Luokka, jossa toteutetaan staattinen tehtävien jako ts. työn tehtävä 1
 * 
* @version     1.0                 
* @since       1.0          
*/

import fi.utu.tech.ThreadRunner.tasks.Countable;
import fi.utu.tech.ThreadRunner.tasks.TaskFactory;

import java.util.ArrayList;

public class StaticDispatcher implements Dispatcher{

	/**
	 * Metodi, jossa on toteutettu staattinen tehtäväjako toiminnallisuus.
	 *
	 *
	 * @param controlSet Käyttöliittymässä asetettu arvot välittyvät tässä oliossa
	 * @return void
	 * 
	 */

	public void dispatch(ControlSet controlSet){
		try {
			Countable co = TaskFactory.createTask(controlSet.getTaskType());
			ArrayList<Integer> taskList = co.generate(controlSet.getAmountTasks(), controlSet.getMaxTime());
			Thread[] threads = new Thread[controlSet.getThreadCount()];
			Thread myThread = Thread.currentThread();

			ArrayList<ArrayList<Integer>> taskSet = getSets(taskList, controlSet.getThreadCount());
			for(int i = 0; i < threads.length; i++){
				threads[i] = new Thread(new Kakunsyöjä(taskSet.get(i), controlSet.getWorkerType()));
				myThread.setPriority(Thread.MIN_PRIORITY);
				threads[i].start();
			}
			System.out.println("Kaikki valmiina.");
			waitThreadsToFinish(threads);
			System.out.println("Kaikki säikeet ovat lopettaneet.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void waitThreadsToFinish(Thread[] threads){
		Thread myThread = Thread.currentThread();
		myThread.setPriority(Thread.MIN_PRIORITY);
		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (threadsAlive(threads));
	}

	private boolean threadsAlive(Thread[] threads){
		for(int i = 0; i < threads.length; i++){
			if(threads[i].isAlive())
				return true;
		}
		return false;
	}

	public ArrayList<ArrayList<Integer>> getSets(ArrayList<Integer> tasks, int threadCount){
		ArrayList<ArrayList<Integer>> tempListSet = new ArrayList<>();

		if(threadCount < 1 || tasks == null ||tasks.isEmpty())
			return tempListSet;

		for(int i = 0; i < threadCount; i++){
			ArrayList<Integer> tempList = new ArrayList<>();

			int tasksForEachList = (int)Math.ceil(tasks.size()/(float)threadCount);
			int tasksForCurrentList = i < (threadCount-1) ?
					tasksForEachList :
					tasks.size() - (tempListSet.size()*tasksForEachList);

			for(int j = 0; j < tasksForCurrentList; j++){
				try {
					tempList.add(tasks.get(i*tasksForEachList+j));
				} catch (Exception e){
					break;
				}
			}
			tempListSet.add(tempList);
		}
		return tempListSet;
	}
}
