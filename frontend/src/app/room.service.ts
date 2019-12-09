import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { AuthService, httpOptions } from "./auth.service";
import { Room } from "./Room";

@Injectable({
  providedIn: "root"
})
export class RoomService {
  private roomUrl: string = "http://localhost:8080/rooms";

  constructor(private http: HttpClient, private authService: AuthService) {}

  getRooms(): Promise<Room[]> {
    return this.http.get<Room[]>(`${this.roomUrl}`, httpOptions).toPromise();
  }

  getRoom(id: number): Promise<Room> {
    return this.http
      .get<Room>(`${this.roomUrl}/${id}`, httpOptions)
      .toPromise();
  }

  createRoom(room: Room): Promise<Room> {
    return this.http
      .post<Room>(`${this.roomUrl}`, room, httpOptions)
      .toPromise();
  }

  updateRoom(room: Room): Promise<Room> {
    return this.http
      .put<Room>(`${this.roomUrl}/${room.id}`, room, httpOptions)
      .toPromise();
  }

  async deleteRoom(room: Room): Promise<Room> {
    return this.http
      .delete<Room>(`${this.roomUrl}/${room.id}`, httpOptions)
      .toPromise();
  }
}
