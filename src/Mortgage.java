public class Mortgage {

    /* Final Exam Problem 5 */

    /*
     *  5a) Pn+1 = 1.005*Pn - p, Po = 200,000; p = ?
     *
     *  5b) Pn+1 = 1.005*Pn - 1500, Po = ? ; p = 1500
     */

    public static void main(String [] args){

        //System.out.println("Find ideal payment for 30 year mortgage");
        //problem5a(100,360,1.005,200000);
        //System.out.println("\n");
        System.out.println("Find maximum initial principal for 30 year mortgage");
        problem5b(150,360,1.005,1500);


        // Testing results

        double P_n = 250187.3779; int months = 360;
        double monthlyPayment = 1500; double interestRate = 1.005;
        for (int i = 0; i < months; i++) {
            P_n = (interestRate * P_n) - monthlyPayment;
            //System.out.println(i + "\t" + P_n);
        }
        //System.out.println("Check, should equal 0 --> " + P_n);


    }

    public static void problem5a(int cycles, int months, double interestRate, double initialPrincipal){
        // Purpose: Find monthlyPayment s.t. P_months = 0 with given interestRate and initialPrincipal

        double monthlyPayment = 1199; // arbitrary starting point
        double P_n = 0; // represents value of P at month n

        // For changing initialPrincipal after each cycle of months
        boolean lastAction = true; // true is up, false is down
        double interval = 200; // how much monthlyPayment will originally increase/decrease

        int count = 0;
        while (count < cycles) {
            P_n = initialPrincipal; // reset

            // System.out.println("Starting a_n = " + a_n);
            // System.out.println("Starting payment = " + p);

            // simulate i months
            for (int i = 0; i < months; i++) {
                P_n = (interestRate * P_n) - monthlyPayment;
                //System.out.println(i + "\t" + P_n);
            }

            //System.out.println("count = " + count );
            //System.out.println("P_months = " + P_n );
            //System.out.println("monthlyPayment was " + monthlyPayment);
            // check if |P_n| > 1 to see whether an optimal monthlyPayment has been found or not
            if (Math.abs(P_n) > 1) {
                // initial value modification
                if (P_n < 0) { // overshot, payment needs to decrease
                    //System.out.println("A: P_n is negative so payment needs to decrease");

                    if (lastAction) { // current action is decrease and past is increase --> decrease less
                        //System.out.println("A1: Payment increased last time so ");
                        interval /= 2;
                        monthlyPayment -= interval;
                    } else { // current action is decrease and past is decrease --> decrease
                        //System.out.println("A2: Payment decreased last time");
                        monthlyPayment -= interval;
                    }
                    lastAction = false; // both are decrease actions, set boolean appropriately
                }
                if (P_n > 0) { // undershot, payment needs to increase
                    //System.out.println("P_n is positive so payment needs to increase");
                    monthlyPayment += interval;
                    if (lastAction) { // current action is increase and past is increase --> increase
                        monthlyPayment += interval;
                    } else { // current action is increase and past is decrease --> increase less
                        interval /= 2;
                        monthlyPayment += interval;
                    }
                    lastAction = true;

                }
            }
            //System.out.println("monthlyPayment is now " + monthlyPayment);
            count++;
        }

        System.out.println("Final Balance: " + P_n + " at time " + months + " months");
        System.out.println("Monthly Payment: " + monthlyPayment);

    }



    public static void problem5b(int cycles, int months, double interestRate, double monthlyPayment) {
        // Purpose: Calculate initialPrincipal s.t. P_months = 0 with given interestRate and monthlyPayment

        double initialPrincipal = 200000; // arbitrary starting point
        double P_n = 0; // represents value of P at month n

        // For changing initialPrincipal after each cycle of months
        boolean lastAction = true; // true is up, false is down
        double interval = 10000; // how much initialPrincipal will originally increase/decrease

        int count = 0;
        while (count < cycles) {
            P_n = initialPrincipal; // reset

            // System.out.println("Starting a_n = " + a_n);
            // System.out.println("Starting payment = " + p);

            // simulate i months
            for (int i = 0; i < months; i++) {
                P_n = (interestRate * P_n) - monthlyPayment;
                // System.out.println(i + "\t" + a_n);
            }

            // check if |P_n| > 1 to see whether an optimal initialPrincipal has been found or not
            if (Math.abs(P_n) > 1) {
                // initial value modification
                if (P_n < 0) { // initial needs to increase
                    //System.out.println("Negative balance, a0 needs to increase");
                    if (lastAction) { // went up last
                        initialPrincipal += interval;
                    } else { // went down last
                        interval /= 2;
                        initialPrincipal += interval;
                    }
                    lastAction = true;
                }
                if (P_n > 0) { // initial needs to decrease
                    //System.out.println("Positive balance, a0 needs to decrease");
                    if (lastAction) { // went up last
                        interval /= 2;
                        initialPrincipal -= interval;
                    } else { // went down last
                        initialPrincipal -= interval;
                    }
                    lastAction = false;
                }
            }
            count++;
        }

        System.out.println("Final Balance: " + P_n + " at time " + months + " months");
        System.out.println("Initial Principal: " + initialPrincipal);

    }

}
