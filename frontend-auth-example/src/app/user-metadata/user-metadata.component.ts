import {Component, OnInit} from '@angular/core';
import {concatMap, pluck, tap} from 'rxjs/operators';

import {HttpClient} from '@angular/common/http';
import {AuthService} from '@auth0/auth0-angular';

@Component({
  selector: 'app-metadata',
  template: `
    <div *ngIf="metadata">
      <pre>{{ metadata | json }}</pre>
    </div>`,
  styles: [],
})
export class UserMetadataComponent implements OnInit {
  metadata: any = null;

  constructor(public auth: AuthService, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.auth.user$
      .pipe(
        concatMap((user) =>
          this.http.get(
            encodeURI(`https://skeleton.eu.auth0.com/api/v2/users/${user.sub}`)
          )
        ),
        pluck('user_metadata'),
        tap((meta) => (this.metadata = meta))
      )
      .subscribe();
  }
}
