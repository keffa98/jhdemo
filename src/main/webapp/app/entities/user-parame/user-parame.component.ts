import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserParame } from 'app/shared/model/user-parame.model';
import { UserParameService } from './user-parame.service';
import { UserParameDeleteDialogComponent } from './user-parame-delete-dialog.component';

@Component({
  selector: 'jhi-user-parame',
  templateUrl: './user-parame.component.html',
})
export class UserParameComponent implements OnInit, OnDestroy {
  userParames?: IUserParame[];
  eventSubscriber?: Subscription;

  constructor(protected userParameService: UserParameService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.userParameService.query().subscribe((res: HttpResponse<IUserParame[]>) => (this.userParames = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserParames();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserParame): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserParames(): void {
    this.eventSubscriber = this.eventManager.subscribe('userParameListModification', () => this.loadAll());
  }

  delete(userParame: IUserParame): void {
    const modalRef = this.modalService.open(UserParameDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userParame = userParame;
  }
}
