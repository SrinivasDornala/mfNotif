
export class User {
  id!: number;
  name!: string;
  email!: string;
  phone!: string;
  admin!: boolean;

  constructor(data: any = {}) {
      this.id = data.id;
      this.name = data.name || '';
      this.email = data.email || '';
      this.phone = data.phone;
      this.admin = data.admin || false;
    }
}
