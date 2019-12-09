import { Course } from "./course";
import { Exam } from './exam';

export class User {
  id: number;
  neptunCode: string;
  name: string;
  password: string;
  type: "STUDENT" | "TEACHER";
  courses: Course[];
  exams: Exam[];
}
