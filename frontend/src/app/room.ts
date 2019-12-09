import { Course } from "./course";

export class Room {
  id?: number;
  name: string;
  maxLimit : number;
  occupied?: boolean;
  courses?: Course[];
}
