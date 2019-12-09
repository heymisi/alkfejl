import {
  Component,
  OnChanges,
  Input,
  Output,
  EventEmitter,
  OnInit
} from "@angular/core";
import { Course } from "../course";
import { NgForm } from "@angular/forms";
import { Room } from "../room";
import { Exam } from '../exam';
import { RoomService } from '../room.service';

@Component({
  selector: 'exam-form',
  templateUrl: './exam-form.component.html',
  styleUrls: ['./exam-form.component.css']
})
export class ExamFormComponent implements OnChanges, OnInit {

  @Input() exam: Exam;

  private rooms : Room[] = [];

  public model: Exam ;
  @Output() onSubmit = new EventEmitter<Exam>();

  constructor(    private roomService: RoomService
    ) {}

  ngOnChanges(): void {
    this.model = Object.assign({}, this.exam);
  }
  async ngOnInit(): Promise<void> {
    this.rooms = await this.roomService.getRooms();

  }

  submit(form: NgForm): void {
    if (!form.valid) {
      console.log("Not valid");
      return;
    }
  
    this.onSubmit.emit( this.model );
  }
  public change(event) : Number{
    return event;
  }
}
