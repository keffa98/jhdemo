import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhdemoTestModule } from '../../../test.module';
import { UserParameDetailComponent } from 'app/entities/user-parame/user-parame-detail.component';
import { UserParame } from 'app/shared/model/user-parame.model';

describe('Component Tests', () => {
  describe('UserParame Management Detail Component', () => {
    let comp: UserParameDetailComponent;
    let fixture: ComponentFixture<UserParameDetailComponent>;
    const route = ({ data: of({ userParame: new UserParame(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhdemoTestModule],
        declarations: [UserParameDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UserParameDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserParameDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userParame on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userParame).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
