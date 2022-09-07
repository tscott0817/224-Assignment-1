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
    ArrayList<Instructor[]> allSets = new ArrayList<>();
    ArrayList<Instructor[]> coveringSets = new ArrayList<>();
    Instructor[] rtnInstructors = new Instructor[instructors.length];

    /*** Get all possible sets with permute() ArrayList ***/
    for (boolean[] arr : instructorPerms) {

      // Get all possible sets
      int boolItr = 0;
      for (boolean value : arr) {
        System.out.println(value);
        if (value == true) {
          tempInstructorList.add(instructors[boolItr]);
        }
        boolItr++;
      }
      System.out.println(tempInstructorList); // Entire array

      // Create temporary array of instructor to test against courses
      int instItr = 0;
      Instructor[] instructorArr = new Instructor[tempInstructorList.size()];
      for (Instructor tempInstructors : tempInstructorList) {
        instructorArr[instItr] = tempInstructors;
        instItr++;
      }

      // Works to add all
      //allSets.add(instructorArr);
      //coveringSets.add(instructorArr);

      // Check how many courses are covered
      ArrayList<String> tempCourses = new ArrayList<>();
      String[] newCourseList = new String[courses.length];
      for (String currCourse : courses) {
        for (Instructor currInstructor : instructorArr) {
          if (currInstructor.canTeach(currCourse) & !tempCourses.contains(currCourse)) {
              tempCourses.add(currCourse);
          }
        }

        int itr25 = 0;
        for (String str: tempCourses) {
          newCourseList[itr25] = str;
          itr25 += 1;
        }
        //System.out.println(courseItr); // Prints how many of each course
      }
      System.out.println(tempCourses);
//      for (String str : newCourseList) {
//        System.out.println(str);
//      }
      //System.out.println(courses);
      System.out.println("\n");

      if (Arrays.equals(newCourseList, courses)) {
        coveringSets.add(instructorArr);
      }
      tempInstructorList.removeAll(tempInstructorList); // Empty temp array for reuse
    }

    for (Instructor[] inst : coveringSets) {
      for (Instructor inst2 : inst) {
        System.out.println(inst2);
      }
      System.out.println("\n");
    }

    /*** Determine which of these sets cover all courses ***/

    /*** Out of remaining sets, find the one with the fewest teachers ***/

    return rtnInstructors;
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
