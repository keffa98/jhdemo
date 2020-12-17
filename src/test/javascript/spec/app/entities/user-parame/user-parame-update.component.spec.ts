import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhdemoTestModule } from '../../../test.module';
import { UserParameUpdateComponent } from 'app/entities/user-parame/user-parame-update.component';
import { UserParameService } from 'app/entities/user-parame/user-parame.service';
import { UserParame } from 'app/shared/model/user-parame.model';

describe('Component Tests', () => {
  describe('UserParame Management Update Component', () => {
    let comp: UserParameUpdateComponent;
    let fixture: ComponentFixture<UserParameUpdateComponent>;
    let service: UserParameService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhdemoTestModule],
        declarations: [UserParameUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UserParameUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserParameUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserParameService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserParame(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserParame();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
