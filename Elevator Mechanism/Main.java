import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // Inner class representing a Floor
    public static class Floor {
        private int destinationFloor;

        public int getDestinationFloor() {
            return destinationFloor;
        }

        public Floor(int destinationFloor) {
            this.destinationFloor = destinationFloor;
        }
    }

    // Inner class representing the Elevator
    public static class Elevator {
        private Queue<Integer> queue = new LinkedList<>();
        private int currentFloor;
        private String direction;

        public Elevator(int currentFloor, String direction) {
            this.currentFloor = currentFloor;
            this.direction = direction;
        }

        public String getDirection() {
            return direction;
        }

        public int getCurrentFloor() {
            return currentFloor;
        }

        public void setCurrentFloor(int f) {
            currentFloor = f;
        }

        public void moveUp() {
            currentFloor++;
        }

        public void moveDown() {
            currentFloor--;
        }

        public void addFloorRequest(int destinationFloor) {
            if (destinationFloor == currentFloor) {
                System.out.println("Elevator is already on Floor " + destinationFloor);
            } else if (destinationFloor < 1 || destinationFloor > 10) {
                System.out.println("Invalid floor number. Please enter a floor between 1 and 10.");
            } else if (destinationFloor < currentFloor) {
                System.out.println("Going down  - Floor " + destinationFloor);
                queue.add(destinationFloor);
            } else {
                System.out.println("Going up  - Floor " + destinationFloor);
                queue.add(destinationFloor);
            }
        }

        public void operate() {
            while (true) {
                while (!queue.isEmpty()) {
                    int nextFloor = queue.poll(); // Take the next floor request from the queue.
                    while (currentFloor != nextFloor) {
                        if (currentFloor < nextFloor) {
                            moveUp();
                            System.out.println("Elevator is going up  - Floor " + currentFloor);
                        } else {
                            moveDown();
                            System.out.println("Elevator is going down  - Floor " + currentFloor);
                        }
                    }
                    System.out.println("Destination reached: Floor " + nextFloor);
                }

                System.out.println("\nAll floor requests completed. Elevator is now at Floor " + currentFloor);

                System.out.print("Do you want to continue? (Enter 'y' to continue or any other key to stop): ");
                Scanner sc = new Scanner(System.in);
                String input = sc.next();
                if (!input.equalsIgnoreCase("y")) {
                    break;
                }

                System.out.print("Enter the destination floor (1 to 10) or 'q' to stop: ");
                input = sc.next();
                if (input.equalsIgnoreCase("q")) {
                    break;
                }
                try {
                    int destinationFloor = Integer.parseInt(input);
                    Floor floor = new Floor(destinationFloor);
                    addFloorRequest(floor.getDestinationFloor());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid floor number or 'q' to stop.");
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the current floor of the elevator (1 to 10): ");
        int currentFloor = sc.nextInt();
        Elevator elevator = new Elevator(currentFloor, "Up");

        System.out.println("\nElevator is ready. Press 'q' to stop the elevator.");

        // Simulate the elevator operation and handle requests from within the elevator.
        while (true) {
            System.out.print("Enter the destination floor (1 to 10) or 'q' to stop: ");
            String input = sc.next();
            if (input.equalsIgnoreCase("q")) {
                break;
            }
            try {
                int destinationFloor = Integer.parseInt(input);
                Floor floor = new Floor(destinationFloor);
                elevator.addFloorRequest(floor.getDestinationFloor());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid floor number or 'q' to stop.");
            }
        }

        elevator.operate();
    }
}
