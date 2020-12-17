import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhdemoSharedModule } from 'app/shared/shared.module';
import { UserParameComponent } from './user-parame.component';
import { UserParameDetailComponent } from './user-parame-detail.component';
import { UserParameUpdateComponent } from './user-parame-update.component';
import { UserParameDeleteDialogComponent } from './user-parame-delete-dialog.component';
import { userParameRoute } from './user-parame.route';

@NgModule({
  imports: [JhdemoSharedModule, RouterModule.forChild(userParameRoute)],
  declarations: [UserParameComponent, UserParameDetailComponent, UserParameUpdateComponent, UserParameDeleteDialogComponent],
  entryComponents: [UserParameDeleteDialogComponent],
})
export class JhdemoUserParameModule {}
