import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserParame } from 'app/shared/model/user-parame.model';

@Component({
  selector: 'jhi-user-parame-detail',
  templateUrl: './user-parame-detail.component.html',
})
export class UserParameDetailComponent implements OnInit {
  userParame: IUserParame | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userParame }) => (this.userParame = userParame));
  }

  previousState(): void {
    window.history.back();
  }
}
