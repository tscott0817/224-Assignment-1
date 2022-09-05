import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  public static void main(String argv[]) {
    testOne();
//  testTwo();

    /*** Permute Tests ***/
//    ArrayList<boolean[]> perm = permute(3);
//    for (boolean[] arr : perm) {
//      for (boolean value : arr) {
//        System.out.println("[" + value + "]");
//      }
//      System.out.println("\n");
//    }
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
    String tomCourses[] = {"ART002", "ART110", "ART125"};
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
    String hiramCourses[] = {"ART001", "ARt110", "ART125"};
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

  public static Instructor[] findMinCover(String[] courses, Instructor[] instructors) {

    ArrayList<boolean[]> instructorPerms = permute(instructors.length);
    ArrayList<Instructor> tempInstructorList = new ArrayList<>();
    ArrayList<Instructor[]> coveringSets = new ArrayList<>();
    Instructor[] rtnInstructors = new Instructor[instructors.length];

    for (boolean[] arr : instructorPerms) {
      int itr = 0;
      for (boolean value : arr) {
        System.out.println("[" + value + "]");

        if (value == true) {
          tempInstructorList.add(instructors[itr]);
        }
        itr += 1;
        System.out.println(tempInstructorList);
      }
      //System.out.println(tempInstructorList);
      System.out.println("\n");

//      Instructor[] newInst = new Instructor[tempInstructorList.size()];
//      int itr2 = 0;
//      for (Instructor tempList : tempInstructorList) {
//        newInst[itr2] = tempList;
//        coveringSets.add(newInst);
//        itr2 += 1;
//      }
      tempInstructorList.removeAll(tempInstructorList);
    }

//    for (Instructor[] cover : coveringSets) {
//      for (Instructor allSets : cover) {
//        System.out.println(allSets);
//      }
//      System.out.println("\n");
//    }
//    System.out.println(instructorList);




//    for (int i = 0; i < instructorPerms.size(); i++) {
//      for (int j = 0; j < instructorPerms.get(i).length; j++) {
//        if (instructorPerms.get(i)[j] == true) {
//          instructorList.add(instructors[j]);
//        }
//      }

//    for (boolean[] boolArr : instructorPerms) {
//      int itr = 0;
//      for (boolean bool : boolArr) {
//        if (bool == true) {
//          instructorList.add(instructors[itr]);
//        }
//        itr++;
//      }
//
//      System.out.println(instructorList);

//      for (String currCourse : courses) {
//        for (Instructor currTeacher : instructorList) {
//          if (currTeacher.canTeach(currCourse)) {
//
//          }
//        }
//
////        if (courseItr == courses.length) {
////          for (int i = 0; i < instructorList.size(); i++) {
////            rtnInstructors[i] = instructorList.get(i);
////          }
////        }
//
//      }
//    }

    return rtnInstructors;
//    for (int currCourse = 0; currCourse < courses.length; currCourse++) {
//
//    }
//    for (String currentCourse : courses) {
//      for (boolean[] bool : instructorPerms) {
//        if (bool == true) {
//
//        }
//      }
//
//    }



  }

  //-----------------------------------------------------------------------

  public static ArrayList<boolean[]> permute(int n) {

    ArrayList<boolean[]> rtnVal = new ArrayList<>();

    if (n == 0) {

      boolean[] emptyArray = new boolean[0];
      rtnVal.add(emptyArray);
    }
    else {

      ArrayList<boolean[]> sublist = permute(n-1);
      for (boolean[] e: sublist) {

        boolean[] a1 = new boolean[n];
        System.arraycopy(e, 0, a1, 0, e.length);
        a1[n-1] = true;
        rtnVal.add(a1);

        boolean[] a2 = new boolean[n];
        System.arraycopy(e, 0, a2, 0, e.length);
        a2[n-1] = false;
        rtnVal.add(a2);
      }
    }
    return rtnVal;
  }
}
