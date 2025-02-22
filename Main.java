import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);

        System.out.println("Hello , welcome to Tic Tac Toa game !");
        System.out.println("Please chose from the menu to begin --");


        char game_board[][] = new char[3][3];
        // empty 2d array to display th e game board
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board.length; j++) {
                game_board[i][j] = ' ';
            }
        }



        /// the user will choose the number of rounds she/he wants to play
        /// 1 rounds or 3 rounds


                int roundEntery=0;
                // a count to detriment the winner based on the symbol
                int Xwin_count = 0;
                int Owin_count = 0;


                while(true) {
                    try {

                        System.out.println("The number of rounds : ");
                        System.out.println("1- One round to determined the winner " + "\n"
                                + "2- Three round to determined the winner");
                        roundEntery = input.nextInt();

                        if (roundEntery == 1 || roundEntery == 2) {
                          break;
                        } else System.out.println("please input 1 or 2 :");
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter numbers only (1 or 2) ");
                        input.nextLine();
                    }
                }
                 /// I defined a Full round variable to control the flow of the game
                 /// if user entery = 1 then the full round will only equal 1 and the program will stop
                 ///else it will run for 3 rounds
                       int fullrounds=0;
                       if (roundEntery == 1) {
                           fullrounds = 1;
                        } else if (roundEntery == 2) {
                           fullrounds = 3;
                        }else System.out.println("Enter 1 or 2");



                                  updateBoard(game_board);
                                 int PlayerOrder = chosenRole();


                                 // the for loop iterate based on the numbers of the full rounds .
                for (int i = 1; i <= fullrounds; i++) {

                    System.out.println("\n" + "---- Round Number : " + i + " ---- ");

                    //the game start
                    char wining_symbol = gameFlow(game_board, PlayerOrder);

                    //the if decide the symbol returned from the game flow method and increase the counter according to it
                    if (wining_symbol == 'X' && PlayerOrder == 1) {
                        Xwin_count++;
                        System.out.println("Congrats, You won this round!");
                        System.out.println("Your score :" + Xwin_count);

                    } else if (wining_symbol == 'X' && PlayerOrder == 2) {
                        Xwin_count++;
                        System.out.println("Ops.. , You lost this round!");


                    } else if (wining_symbol == 'O' && PlayerOrder == 2) {
                        Owin_count++;
                        System.out.println("Congrats, You won this round!");
                        System.out.println("Your score :" + Owin_count);


                    } else if (wining_symbol == 'O' && PlayerOrder == 1) {
                        Owin_count++;
                        System.out.println("Ops.. , You lost this round!");


                    } else  System.out.println(" Its a tie ! " +
                            "\n The scores  :" +"\n"+" X = " +Xwin_count+ "\n"+ " O ="+Owin_count);


// the if below check is there's another round ( i < ) to rest between the 2 rounds , for the final round it won't be needed to reset
                    if (i < fullrounds) {
                        updateBoard(game_board);
                    }

                }



                // This section of code prints the final  win and loose messages  if there's more than 1 round based on the counters and the player chosen role
                if (Xwin_count > Owin_count ) {
                    if (PlayerOrder == 1) {
                        System.out.println("Congrats, You are the final Winner!");
                    } else System.out.println("The computer is the final winner, Try again!");

                } else if (Owin_count > Xwin_count ) {
                    if (PlayerOrder == 2) {
                        System.out.println("Congrats, You are the final Winner!");
                    } else System.out.println("The computer is the final winner, Try again!");

                } else System.out.println("Its a final tie .");


            }












    static void updateBoard(char[][] b) {
        // this method is to full the game board with numbers
        int p = 1;
        // it iterates from 1 to 9 across the array
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                b[i][j] = (char) (p + '0');
                p++;
            }
        }
    }

    static void displayBoard(char[][] g) {
        // this method used to display the board
        System.out.println("\n" + "----Game Board----" + "\n");


        for (int i = 0; i < g.length; i++) {
            System.out.print(" | ");
            for (int j = 0; j < g.length; j++) {
                System.out.print(g[i][j] + " | "); }
            System.out.println();
            System.out.println(" ------------- ");
        }


    }

    static int chosenRole() {

        Scanner input = new Scanner(System.in);
        int l = 0;

        while(true) {
            try {
            /// user will choose if they want to play first with the symbol X or second with O
            ///  the user chose one time only in the begging of the program
            System.out.println("In what Order do you want to play : ");
            System.out.println("1-Play First 'X'  " + "\n" +
                    "2-Play Second 'O' ");
            int roleEntry = input.nextInt();
                if (roleEntry == 1) {
                    l = 1;
                    break;
                } else if (roleEntry == 2) {
                    l = 2;
                    break;
                } else throw new Exception("please enter either 1 or 2");
            } catch (InputMismatchException e) {
                System.out.println("Please input number ");
                input.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine();

            }

        }
        return l;

    }


    static char gameFlow(char[][] b, int playesO) {
// this method introduce the game flow and how it works

        /// if the user choose 1 then they will be the first otherwise the computer will play first
        char C_sybmol = '0';
        char p_symbol = '0';
        if (playesO == 1) {
            p_symbol = 'X';
            C_sybmol = 'O';
        } else if (playesO == 2) {
            C_sybmol = 'X';
            p_symbol = 'O';
        }



        // based on the number of entry  and the condition of the wining position the while loop wil run

        int TurnCount = 1;
        while (TurnCount < 9) {// this count counties until it reaches  9 or winner found
            displayBoard(b);

            if (playesO == 1) {
                playerTurn(b, p_symbol);
                TurnCount++;
                if (win_position(b) != ' ') {///  after each entry I called the method win_position and checked if It's empty or not
                    displayBoard(b);
                    return p_symbol;
                } else {
                    displayBoard(b);
                    computerTurn(b, C_sybmol);
                    TurnCount++;
                    if (win_position(b) != ' ') {
                        displayBoard(b);
                        return C_sybmol;
                    }
                }
            } else if (playesO == 2) {
                computerTurn(b, C_sybmol);
                TurnCount++;

                if (win_position(b) != ' ') {
                    displayBoard(b);
                    return C_sybmol;

                } else {
                    displayBoard(b);
                    playerTurn(b, p_symbol);
                    TurnCount++;
                    if (win_position(b) != ' ') {
                        displayBoard(b);
                        return p_symbol;
                    }
                }
            }
        }
        return ' '; // the winner is not decided
    }


    static void playerTurn(char[][] b, char Psymbol) {
        Scanner input = new Scanner(System.in);
        int move = 0;

// this method takes the char from the user and send it when the method is called
        while (true) {
            try {
                System.out.println("Please enter your next move (1-9) : ");
                move = input.nextInt();
                int row = (move - 1) / 3; // m-1 to find the index then divided by 3 to decide which row it is in since there are 3 rows
                int column = (move - 1) % 3; // m-1 is also to get the index and % 3 is to get the reminder which gives us the column position

                if (move >= 1 && move <= 9 && b[row][column] != 'X' && b[row][column] != 'O') {
                    b[row][column] = Psymbol;
                    System.out.println("You played (" + Psymbol + ") in the position " + move);
                    break;
                } else throw new Exception("please enter a valid number (1-9) : ");
            } catch (InputMismatchException e) {
                System.out.println("please enter numbers only ! ");
                input.nextLine();// to remove the invalid entry and allow user to enter again
            }catch (Exception e) {
                System.out.println(e.getMessage());
                input.nextLine();// to remove the invalid entry and allow user to enter again
            }
        }
    }
        /// the player turn entry

        static void computerTurn ( char[][] b, char Psymbol){

            Random rand = new Random();

            int move = 0;
            while (true) {
                move = rand.nextInt(1, 10);
                int row = (move - 1) / 3; // m-1 to find the index then divided by 3 to decide which row it is in since there are 3 rows
                int column = (move - 1) % 3; // m-1 is also to get the index and % 3 is to get the reminder which gives us the column position

                if (b[row][column] != 'X' && b[row][column] != 'O') { // if the position is empty
                    b[row][column] = Psymbol;
                    System.out.println("Computer played (" + Psymbol + ") in the position " + move);
                    break;
                } else System.out.println("the random number is occupied : ");


            }

        }


        static char win_position(char[][] b){
// check all the possible 8 ways to win


            // return the symbol that occupied all the diagonal positions and wins

            if ((b[0][0] == b[1][1] && b[0][0] == b[2][2]) && b[0][0] != ' ') {
                return b[0][0];
            }
            if ((b[0][2] == b[1][1] && b[0][2] == b[2][0]) && b[0][2] != ' ') {
                return b[0][2];


/// //////////////////////////////////////

                //  check rows
            }
            if ((b[0][0] == b[0][1] && b[0][0] == b[0][2]) && b[0][0] != ' ') {
                return b[0][0];

            }
            if ((b[1][0] == b[1][1] && b[1][0] == b[1][2]) && b[1][0] != ' ') {
                return b[1][0];

            }
            if ((b[2][0] == b[2][1] && b[2][0] == b[2][2]) && b[2][0] != ' ') {
                return b[2][0];

            }

            /// //////////////////////////////////////
            // check column
            if ((b[0][0] == b[1][0] && b[0][0] == b[2][0]) && b[0][0] != ' ') {
                return b[0][0];
            }
            if ((b[0][1] == b[1][1] && b[0][1] == b[2][1]) && b[0][1] != ' ') {
                return b[0][1];

            }
            if ((b[0][2] == b[1][2] && b[0][2] == b[2][2]) && b[0][2] != ' ') {
                return b[0][2];

            }

            return ' '; // if non of the conditions is true then an empty space will return to indicate that there is still no winner
        }

        /// checking  the if winning positions is full or not


    }











