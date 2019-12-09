import {
  Component,
  OnChanges,
  Input,
  Output,
  EventEmitter
} from "@angular/core";
import { Course } from "../course";
import { NgForm } from "@angular/forms";
import { Room } from "../room";
import { Exam } from '../exam';

@Component({
  selector: 'room-form',
  templateUrl: './room-form.component.html',
  styleUrls: ['./room-form.component.css']
})
export class RoomFormComponent implements OnChanges {

  @Input() room: Room;

  public model: Room ;
  @Output() onSubmit = new EventEmitter<Room>();

  constructor() {}

  ngOnChanges(): void {
    this.model = Object.assign({}, this.room);
  }

  submit(form: NgForm): void {
    if (!form.valid) {
      console.log("Not valid");
      return;
    }
  
    this.onSubmit.emit( this.model );
  }

}
