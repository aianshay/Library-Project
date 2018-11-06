package br.com.library.domain2;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LivroUsersId implements Serializable {

	private int livroId;
	private int userId;
	
	public int getLivro() {
		return livroId;
	}
	public void setLivro(int livro) {
		this.livroId = livro;
	}
	public int getUser() {
		return userId;
	}
	public void setUser(int user) {
		this.userId = user;
	}
	
	@Override
	public int hashCode() {
		  return (int)(livroId + userId);
		 }
	
	@Override
	public boolean equals(Object object) {
		  if (object instanceof LivroUsersId) {
		   LivroUsersId otherId = (LivroUsersId) object;
		   return (otherId.livroId == this.livroId) && (otherId.userId == this.userId);
		  }
		  return false;
		 }
	
	
}
