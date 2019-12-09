import { Injectable } from "@angular/core";
import { Course } from "./course";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { AuthService, httpOptions } from "./auth.service";
import { Room } from "./room";
import { RoomService } from './room.service';
import { Exam } from './exam';


@Injectable({
  providedIn: "root"
})
export class ExamService {
  private examUrl: string = "http://localhost:8080/exams";
  private roomUrl: string = "http://localhost:8080/rooms";
  private examRoomUrl: string = "http://localhost:8080/exams/room";
  private userUrl : string = "http://localhost:8080/users";
  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private roomService: RoomService
  ) {}


getRoom(id :number): Promise<Room>{
  return this.http
  .get<Room>(`${this.examRoomUrl}/${id}`, httpOptions)
  .toPromise();
}

async getExams(): Promise<Exam[]> {
  const exams = await this.http
    .get<Exam[]>(`${this.examUrl}`, httpOptions)
    .toPromise();

  exams.forEach(
    async exam =>
      (exam.room = await this.roomService.getRoom(exam.roomNumber))
  );

  return Promise.resolve(exams);
}

  async getExam(id: number): Promise<Exam> {
    const exam = await this.http
      .get<Exam>(`${this.examUrl}/${id}`, httpOptions)
      .toPromise();
    const room = await this.roomService.getRoom(exam.roomNumber);

    return Promise.resolve({ ...exam, room });
  }

  async createExam(exam: Exam): Promise<Exam> {
    const promise2 = this.http
      .post<Course>(`${this.examUrl}`, exam, httpOptions)
      .toPromise();
    return promise2;
  }

  async updateExam(exam: Exam): Promise<Exam> {
    return this.http
      .put<Exam>(`${this.examUrl}/${exam.id}`, exam, httpOptions)
      .toPromise();
  }

  async deleteExam(exam: Exam): Promise<Exam> {
    return this.http
      .delete<Exam>(`${this.examUrl}/${exam.id}`, httpOptions)
      .toPromise();
  }

  async addExamToUser(id: number,exam : Exam): Promise<Exam>{
    console.log(exam)
    return this.http
    .post<Course>(`${this.userUrl}/${id}/exams`,exam,httpOptions).toPromise();
  }

  async getUserExams(id:number) : Promise<Exam[]>{
    const exams = await this.http.get<Exam[]>(`${this.userUrl}/${id}/exams`,httpOptions).toPromise();
    exams.forEach(
      async course =>
        (course.room = await this.roomService.getRoom(course.roomNumber))
    );
    return Promise.resolve(exams);
  
  }
  
  async deleteUserExam(id:number,exam : Exam):Promise<Exam>{
    return this.http
    .put<Exam>(`${this.userUrl}/${id}/exams`,exam,httpOptions).toPromise();
  }
}
