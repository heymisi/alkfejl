import { Room } from './room';
import { User } from './user';

export class Exam {
        public id: number = 0;
        public name: string = "";
        public room: Room ;
        public roomNumber: number;
        public maxLimit: number;
        public date: string;
        public students: User[];
      }

