import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout-user',
  templateUrl: './layout-user.component.html',
  styleUrls: ['./layout-user.component.css'],
})
export class LayoutUserComponent implements OnInit {
  userconnect!: any;
  ngOnInit(): void {
    this.userconnect = JSON.parse(localStorage.getItem('userconnect')!);
  }

  logout() {
    localStorage.clear();
  }
}
