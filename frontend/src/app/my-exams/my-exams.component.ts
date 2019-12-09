import { ExamFormComponent } from './../exam-form/exam-form.component';
import { AuthService } from './../auth.service';
import { RoomService } from './../room.service';
import { ExamService } from './../exam.service';
import { Exam } from './../exam';
import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { CourseService } from '../course.service';
import { Room } from '../room';
import { User } from '../user';

@Component({
  selector: 'app-my-exams',
  templateUrl: './my-exams.component.html',
  styleUrls: ['./my-exams.component.css']
})
export class MyExamsComponent implements OnInit {

  

  public filteredExams: Exam[]= [];
  public selectedStatus: string;
  public selectedExam: Exam;
  private exams: Exam[] = [];
  private user : User;
  private examform : ExamFormComponent;
  constructor(private examService: ExamService,
    private roomService: RoomService,
    private authService: AuthService,) {}

    
  async ngOnInit(): Promise<void> {
    this.selectedStatus = "";
    this.user = this.authService.loggedInUser();
    this.exams = await this.examService.getUserExams(this.user.id);
    console.log(this.exams)
    this.filter();
    
  }

  onFilterChange(status: string): void {
    this.selectedStatus = status;
    this.filter();
  }

  onSelectCourse(exam: Exam): void {
    this.selectedExam = exam;
  }
  onNewClick(): void {
    this.selectedExam = new Exam();
  }

  onDeleteClick(exam: Exam) {
    this.examService.deleteUserExam(this.user.id,exam).then(async () => {
      this.selectedExam = null;
      this.exams = await this.examService.getUserExams(this.user.id);
      this.filter();
    });
  }
  onDeleteClickforTeacher(exam: Exam){
    this.examService.deleteExam(exam).then(async () => {
      this.selectedExam = null;
      this.exams = await this.examService.getUserExams(this.user.id);
      this.filter();
      });
  }

  async onFormSubmit(exam: Exam): Promise<void> {
  
      this.user = this.authService.loggedInUser();

      this.selectedExam.id = Math.floor(Math.random() * 1000000);
      this.selectedExam.roomNumber = 2;
      this.selectedExam.name = exam.name;
      this.selectedExam.maxLimit = exam.maxLimit;
      this.selectedExam.date = exam.date;
      this.selectedExam.room = await this.roomService.getRoom(2);
      this.examService.createExam(this.selectedExam).then(createdCourse => {
        this.exams.push(createdCourse);
      });
      this.examService.addExamToUser(this.user.id,exam);
    
  
  
  }
  private filter(): void {
 
    this.filteredExams =
      this.selectedStatus === ""
        ? this.exams
        : this.exams.filter(
            exam => (exam.date.includes(this.selectedStatus))
          );
  }
  
}
