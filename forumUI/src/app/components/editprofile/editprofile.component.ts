import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { AlertService } from '../../services/alert.service';


@Component({
  selector: 'app-editprofile',
  templateUrl: './editprofile.component.html',
  styleUrls: ['./editprofile.component.css']
})
export class EditprofileComponent implements OnInit {
  loading = false;
  submitted = false;
  userId: any;
  userForm: FormGroup;
  firstName: any;
  lastName: any;
  emailId: any;
  phoneNumber: any;
  isDisabled: boolean = false;
  constructor(private userService: UserService,
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private alertService: AlertService
  ) {
  }

  get f() { return this.userForm.controls; }

  ngOnInit() {
    this.userId = localStorage.getItem('currentUser');
    this.userService.getUser(this.userId)
      .pipe(first())
      .subscribe(
        data => {
          this.firstName = data.firstName;
          this.lastName = data.lastName;
          this.emailId = data.emailId;
          this.phoneNumber = data.phoneNumber;
          this.userForm = this.formBuilder.group({
            firstName: [{ value: this.firstName, disabled: this.isDisabled }, Validators.required],
            lastName: [{ value: this.lastName, disabled: this.isDisabled }, Validators.required],
            emailId: [{ value: this.emailId, disabled: this.isDisabled }, Validators.required],
            phoneNumber: [{ value: this.phoneNumber, disabled: this.isDisabled }, Validators.required],
          });
        },
        error => {
          this.alertService.error("User Not Found");
          return;
        });
  }

  deleteAccount() {
    this.userService.deleteUser(this.userId)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success("Profile Deleted Successfully", true);
        },
        error => {
          this.alertService.error("Profile Deletion Failed");
          return;
        });
    this.authenticationService.logout();
  }

  onSubmit() {
    this.submitted = true;
    if (this.userForm.invalid) {
      return;
    }
    this.loading = true;
    this.userService.updateUser(this.userId, this.userForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success("Profile Updated Successfully", true);
          this.router.navigate(['/myprofile']);
        },
        error => {
          this.alertService.error("Profile Updation Failed");
          this.loading = false;
          return;
        });
  }

}
