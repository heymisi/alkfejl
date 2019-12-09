import { RoomService } from './../room.service';
import { ExamFormComponent } from './../exam-form/exam-form.component';
import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { CourseService } from '../course.service';
import { User } from '../user';
import { Room } from '../room';

@Component({
  selector: 'app-my-courses',
  templateUrl: './my-courses.component.html',
  styleUrls: ['./my-courses.component.css']
})
export class MyCoursesComponent implements OnInit {

  public filteredCourses: Course[] = [];
  public selectedStatus: string;
  public selectedCourse: Course;
  private user: User;
  private courses: Course[] = [];
  private a: ExamFormComponent;

  constructor(private courseService: CourseService,
    private authService : AuthService,
    private roomService: RoomService) {}

  async ngOnInit(): Promise<void> {
    this.selectedStatus = "";
    this.user = this.authService.loggedInUser();
    this.courses = await this.courseService.getUserCourse(this.user.id);
    this.filter();
    
    
  }

  onFilterChange(status: string): void {
    this.selectedStatus = status;
    this.filter();
  }

  onSelectCourse(course: Course): void {
    this.selectedCourse = course;
  }

  async onFormSubmit(course: Course ): Promise<void> {
    if (course.id > 0) {
      await this.courseService.updateCourse(course);
      this.selectedCourse.name = course.name;
      this.selectedCourse.date = course.date;
      this.selectedCourse.maxLimit = course.maxLimit;
      this.selectedCourse.students = course.students;
      this.selectedCourse.roomNumber = 5;
      this.selectedCourse.room = course.room;
    } else {
      this.user = this.authService.loggedInUser();

      this.selectedCourse.id = Math.floor(Math.random() * 1000000);
      this.selectedCourse.roomNumber = 2;
      this.selectedCourse.name = course.name;
      this.selectedCourse.maxLimit = course.maxLimit;
      this.selectedCourse.date = course.date;
      this.selectedCourse.room = await this.roomService.getRoom(2);
      this.courseService.addCourseToUser(this.user.id,this.selectedCourse)

      this.courseService.createCourse(this.selectedCourse).then(createdCourse => {
        this.courses.push(createdCourse);
      });
    }
    this.selectedCourse = null;
  }

  onNewClick(): void {
    this.selectedCourse = new Course();
  }

  onDeleteClick(course: Course) {
    this.courseService.deleteUserCourse(this.user.id,course).then(async () => {
      this.selectedCourse = null;
      this.courses = await this.courseService.getUserCourse(this.user.id);
      this.filter();
    });
  }
  onDeleteClickforTeacher(course: Course){
    this.courseService.deleteCourse(course).then(async () => {
      this.selectedCourse = null;
      this.courses = await this.courseService.getUserCourse(this.user.id);
      this.filter();
      });
  }

  private filter(): void {
    this.filteredCourses =
      this.selectedStatus === ""
        ? this.courses
        : this.courses.filter(
          course => (course.date.includes(this.selectedStatus))
          );
  }
}
