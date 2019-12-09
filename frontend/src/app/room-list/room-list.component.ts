import { Component, OnInit } from "@angular/core";
import { Room } from "../Room";
import { RoomService } from '../room.service';

@Component({
  selector: "app-room-list",
  templateUrl: "./room-list.component.html",
  styleUrls: ["./room-list.component.css"]
})
export class RoomListComponent implements OnInit {
  public filteredRooms: Room[];
  public selectedStatus: string;
  public selectedRoom: Room;

  private rooms: Room[] = [];

  constructor(private roomService: RoomService) {}

  async ngOnInit(): Promise<void> {
    this.selectedStatus = "";
    this.rooms = await this.roomService.getRooms();
    console.log(this.rooms);
    this.filter();
  }

  onFilterChange(status: string): void {
    this.selectedStatus = status;
    this.filter();
  }

  onSelectRoom(room: Room): void {
    this.selectedRoom = room;
  }

  async onFormSubmit(room: Room): Promise<void> {
    if (room.id > 0) {
      await this.roomService.updateRoom(room);
      this.selectedRoom.name = room.name;
      this.selectedRoom.occupied = false;
      this.selectedRoom.courses = room.courses;
    } else {
      this.selectedRoom.id = Math.floor(Math.random() * 1000000);
      this.selectedRoom.name = room.name;
      this.selectedRoom.courses = room.courses;
      this.selectedRoom.occupied = false;
      this.roomService.createRoom(room).then(createdRoom => {
        this.rooms.push(createdRoom);
      });
    }
    this.selectedRoom = null;
  }

  onNewClick(): void {
    this.selectedRoom = new Room();
  }

  onDeleteClick(Room: Room) {
    this.roomService.deleteRoom(Room).then(async () => {
      this.selectedRoom = null;
      this.rooms = await this.roomService.getRooms();
      this.filter();
    });
  }

  private filter(): void {
    this.filteredRooms =
      this.selectedStatus === ""
        ? this.rooms
        : this.rooms.filter(Room => !Room.occupied);
  }
}
