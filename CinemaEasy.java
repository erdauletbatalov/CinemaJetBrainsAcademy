package cinema;

import java.util.Scanner;

public class Cinema {


    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Write your code here


        System.out.println("Enter the number of rows:");
        final int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int seats = scanner.nextInt();



        String[][] cinema = new String[rows + 1][seats + 1];

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (i == 0 && j == 0) {
                    cinema[i][j] = " ";
                } else if (i == 0) {
                    cinema[i][j] = String.format("%d", j);
                } else if (j == 0) {
                    cinema[i][j] = String.format("%d", i);
                } else {
                    cinema[i][j] = "S";
                }
            }
        }


            choice(cinema, rows, seats);


    }




//    // Printing a battlefield
    public static void printSeats(String[][] cinema, int rows, int seats) {
        System.out.println("Cinema:");

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void printTicketPrice(String[][] cinema, int rows, int seats) {
        for (; ; ) {
            try {
                System.out.println("Enter a row number:");
                final int row = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                final int seat = scanner.nextInt();

                if ("B".equals(cinema[row][seat])) {
                    System.out.println("That ticket has already been purchased!");
                } else {

                    if (rows * seats <= 60) {
                        int price = 10;
                        System.out.println("Ticket price: $" + price);
                    } else {
                        if (rows % 2 == 0) {
                            int price;
                            if (row > rows - row) {
                                price = 8;
                            } else {
                                price = 10;
                            }
                            System.out.println("Ticket price: $" + price);
                        } else {
                            int price;
                            if (row < rows - row) {
                                price = 10;
                            } else {
                                price = 8;
                            }
                            System.out.println("Ticket price: $" + price + "\n\n");
                        }
                    }
                    cinema[row][seat] = "B";
                    break;
                }
            } catch (Exception ArrayIndexOutOfBoundsException) {
                System.out.println("Wrong input!");
            }
        }
    }
    public static int choice(String[][] cinema, int rows, int seats) {
        for(;;) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            int n = scanner.nextInt();
            switch (n) {
                case 1:
                    printSeats(cinema, rows, seats);
                    break;
                case 2:
                    printTicketPrice(cinema, rows, seats);
                    break;
                case 3:
                    System.out.println("Number of purchased tickets:" + soldTickets(cinema, rows, seats) + "\n" +
                            "Percentage: " + String.format("%.2f", percentageOfSoldTickets(cinema, rows, seats)) + "%\n" +
                            "Current income: $" + currentIncome(cinema, rows, seats) + "\n" +
                            "Total income: $" + totalIncome(rows, seats));
                    break;
                case 0:
                    return 0;
                default:
                    break;

            }
        }
    }
    public static int currentIncome(String[][] cinema, int rows, int seats) {
        int income = 0;
        for(int i = 0; i <= rows; i++) {
            for(int j = 0; j <= seats; j++) {
                if("B".equals(cinema[i][j])) {
                    income += ticketPrice(rows, seats, i);
                }
            }
        }
        return income;
    }
    public static int ticketPrice( int rows, int seats, int row) {
        if(rows * seats <= 60) return 10;
        else {
            if(rows % 2 == 0) {
                if(row > rows - row) {
                    return 8;
                }
                else {
                    return 10;
                }
            }
            else {
                if(row < rows - row) {
                    return 10;
                }
                else {
                    return 8;
                }
            }
        }
    }
    public static int soldTickets(String[][] cinema, int rows, int seats) {
        int count = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seats; j++) {
                if("B".equals(cinema[i][j])) {
                    count++;
                }
            }
        }

        return count;
    }
    public static double percentageOfSoldTickets(String[][] cinema, int rows, int seats) {
        return (double)(((double)soldTickets(cinema, rows, seats) / ((double)rows * (double)seats)) * 100);
    }
    public static int totalIncome(int rows, int seats) {

        if(rows * seats <= 60) {
            return rows * seats * 10;
        }
        else {
            if(rows % 2 == 0) {
                return  ((rows / 2) * seats * 10) + ((rows / 2) * seats * 8);
            }
            else {
                return  ((rows / 2) * seats * 10) + ((rows / 2 + 1) * seats * 8);
            }
        }
    }
}
