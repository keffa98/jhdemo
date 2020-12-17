import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserParame } from 'app/shared/model/user-parame.model';

type EntityResponseType = HttpResponse<IUserParame>;
type EntityArrayResponseType = HttpResponse<IUserParame[]>;

@Injectable({ providedIn: 'root' })
export class UserParameService {
  public resourceUrl = SERVER_API_URL + 'api/user-parames';

  constructor(protected http: HttpClient) {}

  create(userParame: IUserParame): Observable<EntityResponseType> {
    return this.http.post<IUserParame>(this.resourceUrl, userParame, { observe: 'response' });
  }

  update(userParame: IUserParame): Observable<EntityResponseType> {
    return this.http.put<IUserParame>(this.resourceUrl, userParame, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserParame>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserParame[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
