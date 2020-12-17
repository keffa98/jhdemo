import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhdemoTestModule } from '../../../test.module';
import { UserParameComponent } from 'app/entities/user-parame/user-parame.component';
import { UserParameService } from 'app/entities/user-parame/user-parame.service';
import { UserParame } from 'app/shared/model/user-parame.model';

describe('Component Tests', () => {
  describe('UserParame Management Component', () => {
    let comp: UserParameComponent;
    let fixture: ComponentFixture<UserParameComponent>;
    let service: UserParameService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhdemoTestModule],
        declarations: [UserParameComponent],
      })
        .overrideTemplate(UserParameComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserParameComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserParameService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserParame(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userParames && comp.userParames[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
