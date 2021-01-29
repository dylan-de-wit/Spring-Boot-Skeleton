import {Component} from '@angular/core';
import {AuthService} from '@auth0/auth0-angular';

@Component({
  selector: 'app-user-profile',
  template: `
    <section *ngIf="auth.user$ | async as user">
      <p>{{ user.name }}</p>
      <p>{{ user.email }}</p>
    </section>
  `
})
export class UserProfileComponent {
  constructor(public auth: AuthService) {
    auth.user$.subscribe(user => {
      console.log(user);
    });
  }
}
