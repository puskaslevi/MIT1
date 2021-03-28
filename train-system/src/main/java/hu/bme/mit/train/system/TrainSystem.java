package hu.bme.mit.train.system;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import hu.bme.mit.train.tachograph.TrainTachograph;
import hu.bme.mit.train.user.TrainUserImpl;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TrainSystem {

	private TrainController controller = new TrainControllerImpl();
	private TrainUser user = new TrainUserImpl(controller);
	private TrainSensor sensor = new TrainSensorImpl(controller, user);
	private TrainTachograph tachograph = new TrainTachograph();

	public TrainController getController() {
		return controller;
	}

	public TrainSensor getSensor() {
		return sensor;
	}

	public TrainUser getUser() {
		return user;
	}

	public TrainTachograph getTachograph() { return tachograph;}



	static void main(String[] args){

		System.out.println("Train Speed Controller");
		TrainSystem ts = new TrainSystem();

		while (true){
			Scanner scanner = new Scanner(System.in);
			int option = scanner.nextInt();
			if (option == -1)
				break;
			ts.getUser().overrideJoystickPosition(option);

			System.out.println(ts.getController().getReferenceSpeed());
			scanner.close();
		}


	}

}
