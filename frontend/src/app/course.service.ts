import { Injectable } from "@angular/core";
import { Course } from "./course";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { AuthService, httpOptions } from "./auth.service";
import { Room } from "./room";
import { RoomService } from './room.service';
import { User } from './user';


@Injectable({
  providedIn: "root"
})
export class CourseService {
  private courseUrl: string = "http://localhost:8080/courses";
  private roomUrl: string = "http://localhost:8080/rooms";
  private userUrl: string = "http://localhost:8080/users";
  private examRoomUrl: string = "http://localhost:8080/exams/room";


  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private roomService: RoomService
  ) {}

  async getCourses(): Promise<Course[]> {
    const courses = await this.http
      .get<Course[]>(`${this.courseUrl}`, httpOptions)
      .toPromise();

    courses.forEach(
      async course =>
        (course.room = await this.roomService.getRoom(course.roomNumber))
    );

    return Promise.resolve(courses);
  }
  /*getCourses(): Promise<Course[]> {
    return this.http.get<Course[]>(`${this.courseUrl}`, httpOptions).toPromise();
  }
*/
async addCourseToUser(id: number,course : Course): Promise<Course>{
  return this.http
  .post<Course>(`${this.userUrl}/${id}/courses`,course,httpOptions).toPromise();
}
async addRoomtoCourse(id:number, room: Room): Promise<Room>{
  return this.http
  .post<Course>(`${this.courseUrl}/${id}/room`,room,httpOptions).toPromise();
}
async getUserCourse(id:number) : Promise<Course[]>{
  const courses = await this.http.get<Course[]>(`${this.userUrl}/${id}/courses`,httpOptions).toPromise();
  courses.forEach(
    async course =>
      (course.room = await this.roomService.getRoom(course.roomNumber))
  );
  return Promise.resolve(courses);

}

async deleteUserCourse(id:number,course : Course):Promise<Course>{
  return this.http
  .put<Course>(`${this.userUrl}/${id}/courses`,course,httpOptions).toPromise();
}

  async getCourse(id: number): Promise<Course> {
    const course = await this.http
      .get<Course>(`${this.courseUrl}/${id}`, httpOptions)
      .toPromise();
    const room = await this.roomService.getRoom(course.roomNumber);

    return Promise.resolve({ ...course, room });
  }

  async createCourse(course: Course): Promise<Course> {
    const promise2 = this.http
      .post<Course>(`${this.courseUrl}`, course, httpOptions)
      .toPromise();
    return promise2;
  }

  async updateCourse(course: Course): Promise<Course> {
    return this.http
      .put<Course>(`${this.courseUrl}/${course.id}`, course, httpOptions)
      .toPromise();
  }

  async deleteCourse(course: Course): Promise<Course> {
    return this.http
      .delete<Course>(`${this.courseUrl}/${course.id}`, httpOptions)
      .toPromise();
  }
  getRoom(id :number): Promise<Room>{
    return this.http
    .get<Room>(`${this.examRoomUrl}/${id}`, httpOptions)
    .toPromise();
  }
  
}
