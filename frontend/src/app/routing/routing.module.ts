import { AddExamComponent } from './../add-exam/add-exam.component';
import { AddCourseComponent } from './../add-course/add-course.component';
import { MyExamsComponent } from './../my-exams/my-exams.component';
import { MyCoursesComponent } from './../my-courses/my-courses.component';
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { AuthGuard } from "../auth.guard";
import { LoginFormComponent } from "../login-form/login-form.component";
import { RegisterFormComponent } from "../register-form/register-form.component";
import { RoomListComponent } from "../room-list/room-list.component";


const routes: Routes = [

  {
    path: "",
    redirectTo: "/courses",
    pathMatch: "full"
  },
  {
    path: "courses",
    component: MyCoursesComponent,
    canActivate: [AuthGuard]
  },

  {
    path: "addcourses",
    component: AddCourseComponent,
    canActivate: [ AuthGuard ]

  },
  {
    path: "addexams",
    component: AddExamComponent,
    canActivate: [ AuthGuard ]

  },
  {
    path: "exams",
    component: MyExamsComponent,
    canActivate: [AuthGuard]
  },
 
  {
    path: "login",
    component: LoginFormComponent
  },
  {
    path: "register",
    component: RegisterFormComponent
  },
  {
    path: "rooms",
    component: RoomListComponent,
    canActivate: [AuthGuard]
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class RoutingModule {}
