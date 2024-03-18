
import java.util.Scanner;

        class Appointment {
            //Initilialize variable
            private String month;
            private int day;
            private int hour;
            private int minute;
            private String message;


            public Appointment() {
                //constructors for the code
                this.month = " ";
                this.day = 0;
                this.hour = 0;
                this.minute = 0;
                this.message = " ";
            }

            public Appointment(String month, int day, int hour, int minute, String message) {
                //calling the default constructor
                this();
                this.month = month;
                this.day = day;
                this.hour = hour;
                this.minute = minute;
                this.message = message;

            }
            //Start of my getters and setters
            public String getMonth() {

                return month;
            }


            public void setMonth(String month) {

                this.month = month;
            }

            public int getDay() {

                return day;
            }

            public void setDay(int day) {

                this.day = day;
            }

            public int getHour() {

                return hour;
            }

            public void setHour(int hour) {

                this.hour = hour;
            }

            public int getMinute() {

                return minute;
            }

            public void setMinute(int minute) {

                this.minute = minute;
            }

            public String getMessage() {

                return message;
            }

            public void setMessage(String message) {

                this.message = message;
            }

            public String toString() {

                return String.format("%s %d, %02d:%02d %s", month, day, hour, minute, message);
            }

            public void inputAppointment(Scanner scanner) {
                boolean validInput = false;
                String[] validMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                //This string is used to compare the input to in order to see if they typed a valid month in the correct format

                while (!validInput) {
                    System.out.print("Please enter in a month (3 Letters): ");
                    String inputMonth = scanner.next();
                    boolean isValidMonth = false;
                    for (String month : validMonths) {
                        if (month.equals(inputMonth)) {
                            isValidMonth = true;

                            break;
                        }
                    }
                    if (!isValidMonth) {
                        System.out.println("Please capitalize the first letter and write it in 3 letter form");
                    } else {
                        setMonth(inputMonth);
                        validInput = true;
                    }
                }
                validInput = false;
                while (!validInput) {
                    System.out.println("Please enter in a day: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid whole number");
                        scanner.next();
                    } else {
                        int inputDay = scanner.nextInt();
                        if (inputDay < 1 || inputDay > 31) {
                            System.out.println("Day is out of range of months, please rewrite a new day within the month");
                        } else {
                            setDay(inputDay);
                            validInput = true;
                        }

                    }
                }

                validInput = false;
                while (!validInput) {
                    System.out.println("Please enter hour in military time: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("PLease enter a number");
                        scanner.next();
                    } else {
                        int inputHour = scanner.nextInt();
                        if (inputHour < 0 || inputHour > 23) {
                            System.out.println("Time is not written within the confines (1-23), please write a new time.");
                        } else {
                            setHour(inputHour);
                            validInput = true;
                        }
                    }
                }
                validInput = false;
                while (!validInput) {
                    System.out.println("Please enter in a minute");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Please enter in a number");
                        scanner.next();
                    } else {
                        int inputMinute = scanner.nextInt();
                        if (inputMinute < 0 || inputMinute > 59) {
                            System.out.println("Invalid minute, please enter a minute from 1 to 59");
                        } else {
                            setMinute(inputMinute);
                            validInput = true;
                        }
                    }
                }
                System.out.println("Please enter in task");
                setMessage(scanner.next());

            }
        }

            class Planner {
                private Appointment[] appointments;

                public Planner() {


                    appointments = new Appointment[20];
                    appointments[0] = new Appointment("Mar", 4, 17, 30, "Quiz 1");
                    appointments[1] = new Appointment("Apr", 1, 17, 30, "Midterm 1");
                    appointments[2] = new Appointment("May", 6, 17, 30, "Quiz 2");
                    appointments[3] = new Appointment("Jun", 3, 17, 30, "Final");
                }

                public static boolean compareAppointment(Appointment a1, Appointment a2) {
                    String[] validMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                    int month1Number = 0;
                    int month2Number = 0;

                    //string[] validMonths is reused here because the only way I could think to have the appointments sort in the correct order was to use an array with months in order
                    for (int i = 0; i < 12; ++i) {
                        if (validMonths[i].equals(a1.getMonth())) {
                            month1Number = i;

                        }


                        if (validMonths[i].equals(a2.getMonth())) {
                            month2Number = i;

                        }
                    }

                    if (a1.getMonth().equals(a2.getMonth())) {
                        if (a1.getDay() == a2.getDay()) {
                            if (a1.getHour() == a2.getHour()) {
                                return a1.getMinute() > a2.getMinute();
                            }
                            return a1.getHour() > a2.getHour();
                        }
                        return a1.getDay() > a2.getDay();
                    }
                    return month1Number > month2Number;
                }

                public void insertAppointment(Appointment newAppointment) {
                    //the method used in addAppointment so that it can check a couple of things before it tries to add it to the appointment array

                    int i = 0;
                    while (i < appointments.length && appointments[i] != null && compareAppointment(newAppointment, appointments[i])) {
                        i++;
                    }
                    if (i == appointments.length) {
                        System.out.println("Too many tasks to do, delete one before trying again!");
                    } else {
                        for (int j = appointments.length - 1; j > i; j--) {
                            appointments[j] = appointments[j - 1];
                        }
                        appointments[i] = newAppointment;

                    }
                }



                public void listAppointments() {
                    //shows the list of appointments
                    for (int i = 0; i < appointments.length && appointments[i] != null; i++) {
                        System.out.println((i + 1) + ") " + appointments[i]);
                    }

                }



                public void deleteAppointment(int index) {
                    //deleting an appointment that is in the array
                    if (index >= 1 && index <= appointments.length && appointments[index - 1] != null) {
                        for (int i = index - 1; i < appointments.length - 1 && appointments[i] != null; i++) {
                            appointments[i] = appointments[i + 1];
                        }
                        appointments[appointments.length - 1] = null;
                        System.out.println("Appointment deleted");
                    } else {
                        System.out.println("No task has been associated with this slot " + index);
                    }
                }

                public void addAppointment(Scanner scanner) {
                    //used in the main method, but uses a lot of methods created beforehand in order to actually add an appointment
                    Appointment newAppointment = new Appointment();
                    newAppointment.inputAppointment(scanner);
                    insertAppointment(newAppointment);

                }


                public static void main(String[] args) {
                    //start of main code
                    Planner planner = new Planner();
                    planner.run();
                }

                public void run() {
                    Scanner scanner = new Scanner(System.in);
                    char choice;
                    do {
                        System.out.println("\nMenu:");
                        System.out.println("A)dd Appointment");
                        System.out.println("D)elete Appointment");
                        System.out.println("L)ist Appointments");
                        System.out.println("E)xit");
                        System.out.println("Enter Function: ");
                        choice = scanner.next().charAt(0);

                        switch (choice) {
                            case 'A':
                            case 'a':
                                addAppointment(scanner);
                                break;
                            case 'D':
                            case 'd':
                                System.out.println("Enter the appointments number you want to delete: ");
                                int index = scanner.nextInt();
                                deleteAppointment(index);
                                break;
                            case 'L':
                            case 'l':
                                listAppointments();
                                break;
                            case 'E':
                            case 'e':
                                break;

                        }
                    } while (choice != 'E' && choice != 'e');
                    scanner.close();
                }
            }




















