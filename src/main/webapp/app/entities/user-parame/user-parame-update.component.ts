import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserParame, UserParame } from 'app/shared/model/user-parame.model';
import { UserParameService } from './user-parame.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-user-parame-update',
  templateUrl: './user-parame-update.component.html',
})
export class UserParameUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    titre: [null, [Validators.required]],
    description: [null, [Validators.required]],
    isbn: [null, [Validators.required]],
    code: [null, [Validators.required]],
    user: [],
  });

  constructor(
    protected userParameService: UserParameService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userParame }) => {
      this.updateForm(userParame);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));
    });
  }

  updateForm(userParame: IUserParame): void {
    this.editForm.patchValue({
      id: userParame.id,
      titre: userParame.titre,
      description: userParame.description,
      isbn: userParame.isbn,
      code: userParame.code,
      user: userParame.user,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userParame = this.createFromForm();
    if (userParame.id !== undefined) {
      this.subscribeToSaveResponse(this.userParameService.update(userParame));
    } else {
      this.subscribeToSaveResponse(this.userParameService.create(userParame));
    }
  }

  private createFromForm(): IUserParame {
    return {
      ...new UserParame(),
      id: this.editForm.get(['id'])!.value,
      titre: this.editForm.get(['titre'])!.value,
      description: this.editForm.get(['description'])!.value,
      isbn: this.editForm.get(['isbn'])!.value,
      code: this.editForm.get(['code'])!.value,
      user: this.editForm.get(['user'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserParame>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IUser): any {
    return item.id;
  }
}
