package com.hamsa.streams.project;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		Course pymc = new Course("PYMC","Python MasterClass");
		Course jmc = new Course("JMC","Java MasterClass");
		
//		Student Nithya = new Student("IN",2019,30,"F",true,jmc,pymc);
//		System.out.println(Nithya);
//		Nithya.watchLecture("JMC", 10, 5, 2019);
//		Nithya.watchLecture("PYMC", 7, 7, 2020);
//		System.out.println(Nithya);
		
//		Stream.generate(()-> Student.getRandomStudent(jmc,pymc))
//		               .limit(10)
//		               .forEach(System.out::println);
		
		Student[] students = new Student[1000];
		Arrays.setAll(students,(i) -> Student.getRandomStudent(jmc,pymc));
		
//	    var maleStudents = Stream.generate(()-> Student.getRandomStudent(jmc,pymc))
//                           .limit(1000);
//	    maleStudents = maleStudents.filter(s-> s.getGender().equals("M"));
		var maleStudents = Arrays.stream(students)
				          .filter(s ->s.getGender().equals("M"));
	    
	    System.out.println("# of male students " + maleStudents.count());
	    
	    for( String gender : List.of("Male","Female","Undeclared")) {
	    	var myStudents =Arrays.stream(students)
			          .filter(s ->s.getGender().equals(gender));
	    	System.out.println("# of "  + gender + " students " + myStudents.count());
	    }
		List<Predicate<Student>> list = List.of(
				                        (s) -> s.getAge() < 30,
				                        (Student s) -> s.getAge() >=30  && s.getAge() < 60);
		
		long total = 0;
		for( int i= 0; i < list.size();i++) {
			var myStudents = Arrays.stream(students).filter(list.get(i));
			long cnt = myStudents.count();
			total += cnt;
			System.out.printf("# of students (%s) = %d%n",
					i == 0 ? "<30" :">=30 && <=60",cnt);
		}
		System.out.println("# of students > =60 =  "  + (students.length - total));
		
		var ageStream =Arrays.stream(students).
				       mapToInt(Student::getAgeEnrolled);
		System.out.println("stats for Enrolled Age = " + ageStream.summaryStatistics());
		
		var currentAgeStream =Arrays.stream(students).
			       mapToInt(Student::getAge);
	     System.out.println("stats for Current Age = " + currentAgeStream.summaryStatistics());
	     
	     Arrays.stream(students)
	           .map(Student::getCountryCode)
	           .distinct()
	           .sorted()
	           .forEach(s -> System.out.print(s + " ") );

		    
	     System.out.println();
	     System.out.println("-------------------------");
	     
	     
	     boolean longTerm = Arrays.stream(students)
	    		           .anyMatch(s->(s.getAge() - s.getAgeEnrolled() >= 7) &&
	    		           (s.getMonthsSinceActive() < 12));
	     System.out.println("Longterm Students? " + longTerm);
	     
	     Long longTermCount = Arrays.stream(students)
		                    .filter(s->(s.getAge() - s.getAgeEnrolled() >= 7) &&
		                    (s.getMonthsSinceActive() < 12))
		                    .count();
         System.out.println("Longterm Students? " + longTermCount);
         
         Arrays.stream(students)
                      .filter(s->(s.getAge() - s.getAgeEnrolled() >= 7) &&
                      (s.getMonthsSinceActive() < 12))
                      .filter(s-> !s.hasProgrammingExperience())
                      .limit(5)
                      .forEach(System.out::println);
     // Another method-----
         System.out.println();
	     System.out.println("-------------------------");
         List<Student> longTimeLearners = Arrays.stream(students)
                       .filter(s->(s.getAge() - s.getAgeEnrolled() >= 7) &&
                       (s.getMonthsSinceActive() < 12))
                       .filter(s-> !s.hasProgrammingExperience())
                        .limit(5)
                        .toList();
          System.out.println(longTimeLearners);
          System.out.println("-------------------------");
          var longTimeLearnersArray = Arrays.stream(students)
                  .filter(s->(s.getAge() - s.getAgeEnrolled() >= 7) &&
                  (s.getMonthsSinceActive() < 12))
                  .filter(s-> !s.hasProgrammingExperience())
                   .limit(5)
                   .toArray(Student[]::new);
          
          var learners = Arrays.stream(students)
                  .filter(s->(s.getAge() - s.getAgeEnrolled() >= 7) &&
                  (s.getMonthsSinceActive() < 12))
                  .filter(s-> !s.hasProgrammingExperience())
                   .limit(5)
                   .collect(Collectors.toList());
         
          
      
                
	}

}
