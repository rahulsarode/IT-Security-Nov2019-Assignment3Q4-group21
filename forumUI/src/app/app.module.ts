import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/appcomponent/app.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { UserService } from './services/user.service';
import { AlertComponent } from './components/alert/alert.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AuthenticationService } from './services/authentication.service';
import { AlertService } from './services/alert.service';
import { MyprofileComponent } from './components/myprofile/myprofile.component';
import { EditprofileComponent } from './components/editprofile/editprofile.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    AlertComponent,
    LoginComponent,
    DashboardComponent,
    MyprofileComponent,
    EditprofileComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [UserService, AuthenticationService, AlertService],
  bootstrap: [AppComponent]
})
export class AppModule { }
