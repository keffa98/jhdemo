import { IUser } from 'app/core/user/user.model';

export interface IUserParame {
  id?: number;
  titre?: string;
  description?: string;
  isbn?: string;
  code?: string;
  user?: IUser;
}

export class UserParame implements IUserParame {
  constructor(
    public id?: number,
    public titre?: string,
    public description?: string,
    public isbn?: string,
    public code?: string,
    public user?: IUser
  ) {}
}
