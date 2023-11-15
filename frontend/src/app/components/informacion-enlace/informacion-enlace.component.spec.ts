import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InformacionEnlaceComponent } from './informacion-enlace.component';

describe('InformacionEnlaceComponent', () => {
  let component: InformacionEnlaceComponent;
  let fixture: ComponentFixture<InformacionEnlaceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InformacionEnlaceComponent]
    });
    fixture = TestBed.createComponent(InformacionEnlaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
