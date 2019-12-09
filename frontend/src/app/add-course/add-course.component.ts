import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { CourseService } from '../course.service';
import { User } from '../user';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {

  public filteredCourses: Course[];
  public selectedStatus: string;
  public selectedCourse: Course;
  public user :User = new User();
  private courses: Course[] = [];

  constructor(private courseService: CourseService, private authService : AuthService) {}

  async ngOnInit(): Promise<void> {
    this.user = this.authService.loggedInUser();
    this.selectedStatus = "";
    this.courses = await this.courseService.getCourses();
    this.filter();
  }

  onFilterChange(status: string): void {
    this.selectedStatus = status;
    this.filter();
  }

  onSelectCourse(course: Course): void {
    this.selectedCourse = course;
  }

  async onFormSubmit(course: Course): Promise<void> {
    this.user = this.authService.loggedInUser();
    this.user.courses.push(course);
    this.courseService.addCourseToUser(this.user.id,course);
  }

  onNewClick(): void {
    this.selectedCourse = new Course();
  }

  onDeleteClick(course: Course) {
    this.courseService.deleteCourse(course).then(async () => {
      this.selectedCourse = null;
      this.courses = await this.courseService.getCourses();
      this.filter();
    });
  }

  private filter(): void {
    console.log("valami")
    this.filteredCourses =
      this.selectedStatus === ""
        ? this.courses
        : this.courses.filter(
          course => (course.date.includes(this.selectedStatus))
          );
  }
}
