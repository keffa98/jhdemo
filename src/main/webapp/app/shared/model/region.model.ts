export interface IRegion {
  id?: number;
  codePostal?: number;
  name?: string;
}

export class Region implements IRegion {
  constructor(public id?: number, public codePostal?: number, public name?: string) {}
}
