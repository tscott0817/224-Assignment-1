import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  public static void main(String argv[]) {

    /*** Permute in ascending order or 'true' values ***/
    System.out.println();
    ArrayList<boolean[]> test = permute(3);
    for (boolean[] arr : test) {
      for (boolean value : arr) {
        System.out.println(value);
      }
      System.out.println("\n");
    }

    /*** Tests ***/
    System.out.println("/** Smallest set cover from test one **/");
    testOne();
    System.out.println("\n");

    System.out.println("/** Smallest set cover from test two **/");
    testTwo();

  }

  //--------------------------------------------------------
  // example of how to set up a testcase
  //
  // Frank    GEOG010  GEOG011
  // Cynthia  GEOG010
  // Walter                     GEOG101
  // George                     GEOG101  GEOG201
  //
  // here is the solution (size = 2):
  // Frank: GEOG010 GEOG011
  // George: GEOG101 GEOG201

  public static void testOne() {
    Instructor frank = new Instructor("Frank");
    String frankCourses[] = {"GEOG010", "GEOG011"};
    frank.teach(frankCourses);

    Instructor cynthia = new Instructor("Cynthia");
    String cynthiaCourses[] = {"GEOG010"};
    cynthia.teach(cynthiaCourses);

    Instructor walter = new Instructor("Walter");
    String walterCourses[] = {"GEOG101"};
    walter.teach(walterCourses);

    Instructor george = new Instructor("George");
    String georgeCourses[] = {"GEOG101", "GEOG201"};
    george.teach(georgeCourses);

    Instructor[] instructors = {frank, cynthia, walter, george};
    String[] courses = {"GEOG010", "GEOG011", "GEOG101", "GEOG201"};

    Instructor[] coverSet = findMinCover(courses, instructors);
    for (int i=0; i<coverSet.length; ++i)
      System.out.println(coverSet[i]);

  }

  //-----------------------------------------------------------------------
  // Write this testcase to mode the example from the assignment spec
  //
  // here's the unique solution (of size = 4):
  // John:  ART002 ART008 ART125
  // Betsy: ART124 ART125 ART201
  // Hiram: ART001 ART110 ART125
  // Ralph: ART008 ART064 ART205 ART266

  public static void testTwo() {

    Instructor john = new Instructor("John");
    String johnCourses[] = {"ART002", "ART008", "ART125"};
    john.teach(johnCourses);

    Instructor tom = new Instructor("Tom");
    String tomCourses[] = {"ART008", "ART110", "ART125"};
    tom.teach(tomCourses);

    Instructor mary = new Instructor("Mary");
    String maryCourses[] = {"ART008", "ART266"};
    mary.teach(maryCourses);

    Instructor alicia = new Instructor("Alicia");
    String aliciaCourses[] = {"ART064", "ART110", "ART125", "ART266"};
    alicia.teach(aliciaCourses);

    Instructor betsy = new Instructor("Betsy");
    String betsyCourses[] = {"ART124", "ART125", "ART201"};
    betsy.teach(betsyCourses);

    Instructor kira = new Instructor("Kira");
    String kiraCourses[] = {"ART008", "ART266"};
    kira.teach(kiraCourses);

    Instructor hiram = new Instructor("Hiram");
    String hiramCourses[] = {"ART001", "ART110", "ART125"};
    hiram.teach(hiramCourses);

    Instructor simon = new Instructor("Simon");
    String simonCourses[] = {"ART001", "ART008", "ART125"};
    simon.teach(simonCourses);

    Instructor viggo = new Instructor("Viggo");
    String viggoCourses[] = {"ART110", "ART124", "ART125"};
    viggo.teach(viggoCourses);

    Instructor ralph = new Instructor("Ralph");
    String ralphCourses[] = {"ART008", "ART064", "ART205", "ART266"};
    ralph.teach(ralphCourses);

    Instructor[] instructors = {john, tom, mary, alicia, betsy, kira, hiram, simon, viggo, ralph};
    String[] courses = {"ART001", "ART002", "ART008", "ART064", "ART110", "ART124", "ART125", "ART201", "ART205", "ART266"};
    Instructor[] coverSet = findMinCover(courses, instructors);
    for (int i=0; i<coverSet.length; ++i)
      System.out.println(coverSet[i]);
  }

  //-----------------------------------------------------------------------

  /*** Extra Credit ***/
  public static Instructor[] findMinCover(String[] courses, Instructor[] instructors) {

    ArrayList<boolean[]> instructorPerms = permute(instructors.length);
    ArrayList<Instructor> tempInstructorList = new ArrayList<>();
    ArrayList<Instructor[]> minSet = new ArrayList<>();
    Instructor[] rtnInstructors;

    for (boolean[] arr : instructorPerms) {
      int boolItr = 0; // Tracks the current array of boolean values

      /*** Get all possible instructor sets ***/
      for (boolean value : arr) {
        if (value) {
          tempInstructorList.add(instructors[boolItr]);
        }
        boolItr++;
      }

      /*** Create temporary array of instructors to test against courses ***/
      int instructorItr = 0;
      Instructor[] tempInstructorArr = new Instructor[tempInstructorList.size()];
      for (Instructor tempInstructors : tempInstructorList) {
        tempInstructorArr[instructorItr] = tempInstructors;
        instructorItr++;
      }

      /*** Check how many courses are covered by current temp array of instructors ***/
      ArrayList<String> tempCourses = new ArrayList<>(); // This ArrayList is so we can call .contains() on temp list rather than looping
      String[] tempCourseList = new String[courses.length];
      for (String currCourse : courses) {
        for (Instructor currInstructor : tempInstructorArr) {
          if (currInstructor.canTeach(currCourse) & !tempCourses.contains(currCourse)) {
            tempCourses.add(currCourse);
          }
        }
        int coursesItr = 0;
        for (String str : tempCourses) { // Add each course to temp course array
          tempCourseList[coursesItr] = str;
          coursesItr++;
        }
      }

      /*** When first cover set is found add to ArrayList (to access outside of loop) and stop searching boolean[] ***/
      if (Arrays.equals(tempCourseList, courses)) {
        minSet.add(tempInstructorArr);
        break;
      }
      tempInstructorList.removeAll(tempInstructorList); // Empty temp array for reuse
    }
    rtnInstructors = minSet.get(0); // Retrieve instructor from ArrayList

    return  rtnInstructors;
  }
  //-----------------------------------------------------------------------

  public static ArrayList<boolean[]> permute(int n) {

    ArrayList<boolean[]> tempRtn = new ArrayList<>();
    ArrayList<boolean[]> rtnVal = new ArrayList<>();

    /*** Get all permutations of booleans ***/
    if (n == 0) {
      boolean[] emptyArray = new boolean[0];
      tempRtn.add(emptyArray);
    }
    else {
      ArrayList<boolean[]> sublist = permute(n-1);
      for (boolean[] e: sublist) {

        boolean[] a1 = new boolean[n];
        System.arraycopy(e, 0, a1, 0, e.length);
        a1[n-1] = true;
        tempRtn.add(a1);

        boolean[] a2 = new boolean[n];
        System.arraycopy(e, 0, a2, 0, e.length);
        a2[n-1] = false;
        tempRtn.add(a2);
      }
    }

    /*** Get the number of 'true' values and index positions of all permutations ***/
    ArrayList<Integer> numTrue = new ArrayList<>(); // Holds all sets of 'true' value totals
    ArrayList<Integer> indexValues = new ArrayList<>();
    int indexItr = 0;
    for (boolean[] arr : tempRtn) {
      int trueItr = 0; // Holds number of true values per each individual set
      for (boolean value : arr) {
        if (value == true) {
          trueItr++;
        }
      }
      indexValues.add(indexItr);
      numTrue.add(trueItr);
      indexItr++;
    }

    /*** Sort index positions based on the ascending number of 'true' values per set ***/
    for(int i = 0; i < numTrue.size(); i++){
      for(int j = i; j < numTrue.size(); j++){
        if(numTrue.get(i) > numTrue.get(j)){

          // To keep ordering correct in loop
          int temp1 = numTrue.get(i);
          numTrue.set(i, numTrue.get(j));
          numTrue.set(j, temp1);

          // To add appropriate index
          int temp2 = indexValues.get(i);
          indexValues.set(i, indexValues.get(j));
          indexValues.set(j, temp2);

        }
      }
    }

    /*** Use those index values to fill returned ArrayList ***/
    for (int i = 0; i < tempRtn.size(); i++) {
      rtnVal.add(tempRtn.get(indexValues.get(i)));
    }

    return rtnVal;
  }

//  /*** Regular Assignment ***/
//  public static Instructor[] findMinCover(String[] courses, Instructor[] instructors) {
//
//    ArrayList<boolean[]> instructorPerms = permute(instructors.length);
//    ArrayList<Instructor> tempInstructorList = new ArrayList<>();
//    ArrayList<Instructor[]> coverSets = new ArrayList<>();
//    ArrayList<Integer> totalTrue = new ArrayList<>();
//    Instructor[] rtnInstructors;
//
//    for (boolean[] arr : instructorPerms) {
//      int boolItr = 0; // Tracks the current array of boolean values
//      int trueQuant = 0; // Tracks number of true values per boolean array
//
//      /*** Get all possible sets and their number of true values ***/
//      for (boolean value : arr) {
//        if (value == true) {
//          tempInstructorList.add(instructors[boolItr]);
//          trueQuant++;
//        }
//        boolItr++;
//      }
//
//      /*** Create temporary array of instructors to test against courses ***/
//      int instructorItr = 0;
//      Instructor[] tempInstructorArr = new Instructor[tempInstructorList.size()];
//      for (Instructor tempInstructors : tempInstructorList) {
//        tempInstructorArr[instructorItr] = tempInstructors;
//        instructorItr++;
//      }
//
//      /*** Check how many courses are covered by current temp array of instructors ***/
//      ArrayList<String> tempCourses = new ArrayList<>(); // This ArrayList is so we can call .contains() on temp list rather than looping
//      String[] tempCourseList = new String[courses.length];
//      for (String currCourse : courses) {
//        for (Instructor currInstructor : tempInstructorArr) {
//          if (currInstructor.canTeach(currCourse) & !tempCourses.contains(currCourse)) {
//            tempCourses.add(currCourse);
//          }
//        }
//        int coursesItr = 0;
//        for (String str : tempCourses) { // Add each course to temp course array
//          tempCourseList[coursesItr] = str;
//          coursesItr++;
//        }
//      }
//
//      /*** Add instructors and number of bool values to arrays if a cover set is found ***/
//      if (Arrays.equals(tempCourseList, courses)) {
//        coverSets.add(tempInstructorArr);
//        totalTrue.add(trueQuant);
//      }
//      tempInstructorList.removeAll(tempInstructorList); // Empty temp array for reuse
//    }
//
//    /*** Get the smallest of the cover sets and return it ***/
//    int minCover = totalTrue.get(0);
//    for (int i = 0; i < totalTrue.size(); i++) { // TODO: For each loop returns out of bounds?
//      if (minCover > totalTrue.get(i)) {
//        minCover = totalTrue.get(i);
//      }
//    }
//    int indexPos = totalTrue.indexOf(minCover); // Get the index position of minimal value
//    rtnInstructors = coverSets.get(indexPos); // Retrieve the appropriate instructor based on minimal value index
//
//    return  rtnInstructors;
//  }
//  //-----------------------------------------------------------------------
//
//  public static ArrayList<boolean[]> permute(int n) {
//
//    ArrayList<boolean[]> rtnVal = new ArrayList<>();
//
//    if (n == 0) {
//
//      boolean[] emptyArray = new boolean[0];
//      rtnVal.add(emptyArray);
//    }
//    else {
//
//      ArrayList<boolean[]> sublist = permute(n-1);
//      for (boolean[] e: sublist) {
//
//        boolean[] a1 = new boolean[n];
//        System.arraycopy(e, 0, a1, 0, e.length);
//        a1[n-1] = true;
//        rtnVal.add(a1);
//
//        boolean[] a2 = new boolean[n];
//        System.arraycopy(e, 0, a2, 0, e.length);
//        a2[n-1] = false;
//        rtnVal.add(a2);
//      }
//    }
//    return rtnVal;
//  }
}


