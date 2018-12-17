package rush.entidades;

import org.bukkit.inventory.ItemStack;

import rush.Main;
import rush.utils.serializer.Serializer;
import rush.utils.serializer.SerializerNEW;
import rush.utils.serializer.SerializerOLD;

public class Kit {

	private String id;
	private String nome;
	private String permissao;
	private long delay;
	private ItemStack[] itens;
	private int amountItens;
	
	public Kit(String id, String permissao, String nome, long delay, String itens) {
		ItemStack[] itensStack = getItensByData(itens);
		this.id = id;
		this.permissao = permissao;
		this.nome = nome;
		this.delay = delay;
		this.itens = itensStack;
		this.amountItens = calcule(itensStack);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String kit) {
		this.id = kit;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public ItemStack[] getItens() {
		return itens;
	}

	public void setItens(ItemStack[] itens) {
		this.itens = itens;
		this.amountItens = calcule(itens);
	}
	
	public int getAmountItens() {
		return amountItens;
	}
	
	private int calcule(ItemStack[] itens) {
		int amount = 0;
		for (ItemStack item : itens) {
			if (item != null) amount++;
		}
		return amount;
	}
	
	private ItemStack[] getItensByData(String data) {
		if (Main.isOldVersion()) 
		{
			return SerializerOLD.deserializeListItemStack(data);
		}
		else if (Main.isNewVersion()) 
		{
			return SerializerNEW.deserializeListItemStack(data);
		}
		else 
		{
			return Serializer.deserializeListItemStack(data);
		}
	}

	@Override
	public String toString() {
		return "Kit [permissao=" + permissao + ", delay=" + delay + ", itens=" + itens + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kit other = (Kit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
