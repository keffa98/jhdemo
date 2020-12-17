import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserParame } from 'app/shared/model/user-parame.model';
import { UserParameService } from './user-parame.service';

@Component({
  templateUrl: './user-parame-delete-dialog.component.html',
})
export class UserParameDeleteDialogComponent {
  userParame?: IUserParame;

  constructor(
    protected userParameService: UserParameService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userParameService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userParameListModification');
      this.activeModal.close();
    });
  }
}
