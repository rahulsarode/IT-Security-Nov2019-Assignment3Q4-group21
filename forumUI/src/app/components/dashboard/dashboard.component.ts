import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AlertService } from '../../services/alert.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  userId: any;
  constructor(
    private router: Router,
    private alertService: AlertService
  ) {
    this.userId = localStorage.getItem('currentUser');
    if (!this.userId) {
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {

  }

}
