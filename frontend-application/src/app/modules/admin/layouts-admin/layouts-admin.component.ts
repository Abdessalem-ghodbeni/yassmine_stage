import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layouts-admin',
  templateUrl: './layouts-admin.component.html',
  styleUrls: ['./layouts-admin.component.css'],
})
export class LayoutsAdminComponent implements OnInit {
  userconnect!: any;
  ngOnInit(): void {
    this.userconnect = JSON.parse(localStorage.getItem('userconnect')!);
  }

  logout() {
    localStorage.clear();
  }
}
