import {Component, OnInit} from '@angular/core';
import {AuthService} from "@auth0/auth0-angular";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-user-list',
  template: `
    <h2 style="margin-top: 1rem">Test user list: </h2>
    <section *ngIf="users.length > 0">
      <p *ngFor="let user of users">
        {{user}}
      </p>
    </section>
  `,
})
export class UserListComponent implements OnInit {
  users = [];

  constructor(public auth: AuthService, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get(encodeURI(`${environment.apiUri}/users`)).subscribe((users: any) => this.users = users);
  }

}
