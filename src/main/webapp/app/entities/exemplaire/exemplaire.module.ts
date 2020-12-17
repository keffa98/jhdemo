import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhdemoSharedModule } from 'app/shared/shared.module';
import { ExemplaireComponent } from './exemplaire.component';
import { ExemplaireDetailComponent } from './exemplaire-detail.component';
import { ExemplaireUpdateComponent } from './exemplaire-update.component';
import { ExemplaireDeleteDialogComponent } from './exemplaire-delete-dialog.component';
import { exemplaireRoute } from './exemplaire.route';

@NgModule({
  imports: [JhdemoSharedModule, RouterModule.forChild(exemplaireRoute)],
  declarations: [ExemplaireComponent, ExemplaireDetailComponent, ExemplaireUpdateComponent, ExemplaireDeleteDialogComponent],
  entryComponents: [ExemplaireDeleteDialogComponent],
})
export class JhdemoExemplaireModule {}
