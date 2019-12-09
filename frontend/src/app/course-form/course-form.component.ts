import { RoomService } from './../room.service';
import {
  Component,
  OnChanges,
  Input,
  Output,
  OnInit,
  EventEmitter
} from "@angular/core";
import { Course } from "../course";
import { NgForm } from "@angular/forms";
import { Room } from "../room";

@Component({
  selector: 'course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.css']
})
export class CourseFormComponent implements OnChanges,OnInit {

  @Input() course: Course;

  private rooms : Room[] = [];

  public model: Course ;
  @Output() onSubmit = new EventEmitter<Course>();

  constructor(private roomService :RoomService) {}

  async ngOnInit(): Promise<void> {
    this.rooms = await this.roomService.getRooms();

  }
  ngOnChanges(): void {
    this.model = Object.assign({}, this.course);
  }

  submit(form: NgForm): void {
    if (!form.valid) {
      console.log("Not valid");
      return;
    }
  
    this.onSubmit.emit( this.model );
  }
}
