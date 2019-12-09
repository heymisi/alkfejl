import { User } from "./user";
import { Room } from "./room";

export class Course {
  public id: number = 0;
  public name: string = "";
  public roomNumber: number;
  public room: Room;
  public maxLimit: number;
  public date: string;
  public students: User[];
}
