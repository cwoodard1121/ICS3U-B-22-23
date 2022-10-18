package bayviewglenpools;

import java.util.Scanner;


    public class Pool {

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);

            System.out.println("enter the length of the transition in meters: ");
            double transitionSlope = in.nextDouble();

            System.out.println("enter length of shallow end in meters: ");
            double shallowLength = in.nextDouble();

            System.out.println("please put price of liner per meter squared: ");
            double PriceOfLiner = in.nextDouble();

            System.out.println("put the length of the pool in meters: ");
            double length = in.nextDouble();

            System.out.println("set the width in meters: ");
            double width = in.nextDouble();

            System.out.println("enter the depth of the shallow end (meters) ");
            double heightOfShallowEnd = in.nextDouble();

            System.out.println("Enter deep end depth (meters) ");
            double heightOfDeepEnd = in.nextDouble();




            /**
             * ^ This code sets variables from the user.
             */


            /**
             * Here we are getting the volume of the pool.
             */
            double transitionHeight = (double) heightOfDeepEnd-heightOfShallowEnd;
            // sqrt
            double lengthOfTransition = Math.sqrt(Math.pow(transitionSlope,2) - Math.pow(transitionHeight, 2));
            double lengthOfDeepEnd = (double)length - lengthOfTransition - shallowLength;

            double volume = (lengthOfDeepEnd * heightOfDeepEnd * width) + (shallowLength * heightOfShallowEnd * width) + (heightOfShallowEnd * width * lengthOfTransition) + (0.5 * transitionHeight * lengthOfTransition * width);

            double lNeeded = 1000*(volume*0.9);
            double lNeededRounded = Math.round(lNeeded*100)/100.0;
            // rounding


            System.out.println("The amount of water to keep pool at 90% is: " + lNeededRounded + "L.");

            //Step 4: Calculate the surface area by getting the surface area of the shallow end, deep end, and transition.
            double SurfaceArea = ((heightOfDeepEnd * width) + (2 * (lengthOfDeepEnd * heightOfDeepEnd)) + (lengthOfDeepEnd * width))
                    + ((heightOfShallowEnd * width) + (2*(shallowLength*heightOfShallowEnd)) + (shallowLength * width)) +
                    ((transitionSlope*width) + (2 * (transitionHeight*lengthOfTransition) /2 ) + ( 2 * (lengthOfTransition*heightOfShallowEnd)));

            double SurfaceAreaRounded = Math.round(SurfaceArea*100)/100.0;

            System.out.println("the amount of lining needed is: " + SurfaceAreaRounded + "m^2");

            //calc the cost of the liner
            double liner = SurfaceAreaRounded*PriceOfLiner;

            System.out.println("the lining price is : $" + liner + ".");

            in.close();

        }
    }
