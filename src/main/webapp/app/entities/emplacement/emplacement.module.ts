import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhdemoSharedModule } from 'app/shared/shared.module';
import { EmplacementComponent } from './emplacement.component';
import { EmplacementDetailComponent } from './emplacement-detail.component';
import { EmplacementUpdateComponent } from './emplacement-update.component';
import { EmplacementDeleteDialogComponent } from './emplacement-delete-dialog.component';
import { emplacementRoute } from './emplacement.route';

@NgModule({
  imports: [JhdemoSharedModule, RouterModule.forChild(emplacementRoute)],
  declarations: [EmplacementComponent, EmplacementDetailComponent, EmplacementUpdateComponent, EmplacementDeleteDialogComponent],
  entryComponents: [EmplacementDeleteDialogComponent],
})
export class JhdemoEmplacementModule {}
