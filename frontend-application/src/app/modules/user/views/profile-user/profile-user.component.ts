import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile-user',
  templateUrl: './profile-user.component.html',
  styleUrls: ['./profile-user.component.css'],
})
export class ProfileUserComponent implements OnInit {
  userconnect!: any;
  ngOnInit(): void {
    this.userconnect = JSON.parse(localStorage.getItem('userconnect')!);
  }
}
