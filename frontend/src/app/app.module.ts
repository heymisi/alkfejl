import { ExamFormComponent } from './exam-form/exam-form.component';
import { RoomFormComponent } from './room-form/room-form.component';
import { CourseFormComponent } from './course-form/course-form.component';
import { AddExamComponent } from './add-exam/add-exam.component';
import { AddCourseComponent } from './add-course/add-course.component';
import { MyExamsComponent } from './my-exams/my-exams.component';
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FlexLayoutModule } from "@angular/flex-layout";
import { HttpClientModule } from "@angular/common/http";

import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

import {
  MatToolbarModule,
  MatButtonToggleModule,
  MatIconModule,
  MatButtonModule,
  MatMenuModule,
  MatFormFieldModule,
  MatInputModule,
  MatTable,
  MatOption,
  MatOptionModule,
  MatSelectModule,
} from "@angular/material";

import { AppComponent } from "./app.component";
import { StatusFilterComponent } from "./status-filter/status-filter.component";
import { RoutingModule } from "./routing/routing.module";
import { LoginFormComponent } from "./login-form/login-form.component";
import { RegisterFormComponent } from './register-form/register-form.component';
import { RoomListComponent } from './room-list/room-list.component';
import { MyCoursesComponent } from './my-courses/my-courses.component';

@NgModule({
  declarations: [
    AppComponent,
    StatusFilterComponent,
    LoginFormComponent,
    RegisterFormComponent,
    RoomListComponent,
    MyCoursesComponent,
    MyExamsComponent,
    AddCourseComponent,
    AddExamComponent,
    CourseFormComponent,
    RoomFormComponent,
    ExamFormComponent
    
  ],
  imports: [
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    HttpClientModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonToggleModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatMenuModule,
    RoutingModule,
    MatOptionModule,
    MatSelectModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
