import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { Course } from '../course';
import { Exam } from '../exam';
import { RoomService } from '../room.service';
import { ExamService } from '../exam.service';
import { User } from '../user';

@Component({
  selector: 'app-add-exam',
  templateUrl: './add-exam.component.html',
  styleUrls: ['./add-exam.component.css']
})
export class AddExamComponent implements OnInit {

  
  public filteredExams: Exam[];
  public selectedStatus: string;
  public selectedExam: Exam;
  private exams: Exam[] = [];
  private user: User;
  constructor(private examService: ExamService,
    private roomService: RoomService,
    private authService : AuthService) {}

    
  async ngOnInit(): Promise<void> {
    this.user = this.authService.loggedInUser();
    this.selectedStatus = "";
    this.exams = await this.examService.getExams();
    this.filter();
  }

  onFilterChange(status: string): void {
    this.selectedStatus = status;
    this.filter();
  }

  onSelectCourse(exam: Exam): void {
    this.selectedExam = exam;
  }

  async onFormSubmit(exam: Exam): Promise<void> {
    this.user = this.authService.loggedInUser();
    this.user.exams.push(exam);
    this.examService.addExamToUser(this.user.id,exam);
  }

  onNewClick(): void {
    this.selectedExam = new Exam();
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
